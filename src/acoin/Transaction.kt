package acoin

import java.security.PrivateKey
import java.security.PublicKey

data class TransactionOutput(
    val recipient: PublicKey,
    val amount: Int,
    val transactionHash: String,
    var hash: String = ""
) {
    init {
        hash = "${recipient.encodeToString()}$amount$transactionHash".hash()
    }

    // Add isMine function
    fun isMine(me: PublicKey): Boolean {
        return recipient == me
    }
}

data class Transaction(
    val sender: PublicKey,
    val recipient: PublicKey,
    val amount: Int,
    var hash: String = "",
    val inputs: MutableList<TransactionOutput> = mutableListOf(),
    val outputs: MutableList<TransactionOutput> = mutableListOf()
) {
    private var signature: ByteArray = ByteArray(0)

    init {
        hash = calculateHash()
    }

    private fun calculateHash(): String {
        return "${sender.encodeToString()}${recipient.encodeToString()}$amount${inputs.joinToString("") { it.hash }}${outputs.joinToString("") { it.hash }}${salt}".hash()
    }

    companion object {
        fun create(sender: PublicKey, recipient: PublicKey, amount: Int): Transaction {
            return Transaction(sender, recipient, amount)
        }

        var salt: Long = 0
            get() {
                field += 1
                return field
            }
    }

    fun sign(privateKey: PrivateKey): Transaction {
        signature = "${sender.encodeToString()}${recipient.encodeToString()}$amount".sign(privateKey)
        return this
    }

    fun isSignatureValid(): Boolean {
        return "${sender.encodeToString()}${recipient.encodeToString()}$amount".verifySignature(sender, signature)
    }
}
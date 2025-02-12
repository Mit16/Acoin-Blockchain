package acoin

import java.time.Instant

data class Block(
    val previousHash: String,
    val timestamp: Long = Instant.now().toEpochMilli(),
    val nonce: Long = 0,
    var hash: String = "",
    val transactions: MutableList<Transaction> = mutableListOf()
) {
    init {
        hash = calculateHash()
    }

    fun calculateHash(): String {
        return "$previousHash${transactions.joinToString("") { it.hash }}$timestamp$nonce".hash()
    }

    fun addTransaction(transaction: Transaction): Block {
        if (transaction.isSignatureValid()) {
            transactions.add(transaction)
        }
        return this
    }
}

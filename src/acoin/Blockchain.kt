package acoin

class BlockChain {
    private var blocks: MutableList<Block> = mutableListOf()
    private val difficulty = 2
    private val validPrefix = "0".repeat(difficulty)
    var UTXO: MutableMap<String, TransactionOutput> = mutableMapOf() // Add UTXO property

    fun isValid(): Boolean {
        when {
            blocks.isEmpty() -> return true
            blocks.size == 1 -> return blocks[0].hash == blocks[0].calculateHash()
            else -> {
                for (i in 1 until blocks.size) {
                    val previousBlock = blocks[i - 1]
                    val currentBlock = blocks[i]

                    when {
                        currentBlock.hash != currentBlock.calculateHash() -> return false
                        currentBlock.previousHash != previousBlock.hash -> return false
                        !(isMined(previousBlock) && isMined(currentBlock)) -> return false
                        !currentBlock.transactions.all { it.isSignatureValid() } -> return false
                    }
                }
                return true
            }
        }
    }

    fun add(block: Block): Block {
        val minedBlock = if (isMined(block)) block else mine(block)
        blocks.add(minedBlock)
        updateUTXO(minedBlock)
        return minedBlock
    }

    private fun isMined(block: Block): Boolean {
        return block.hash.startsWith(validPrefix)
    }

    private fun mine(block: Block): Block {
        println("Mining: $block")

        var minedBlock = block.copy()
        while (!isMined(minedBlock)) {
            minedBlock = minedBlock.copy(nonce = minedBlock.nonce + 1)
            minedBlock.hash = minedBlock.calculateHash()
        }

        println("Mined: $minedBlock")
        return minedBlock
    }

    private fun updateUTXO(block: Block) {
        block.transactions.forEach { tx ->
            tx.inputs.forEach { input -> UTXO.remove(input.hash) }
            tx.outputs.forEach { output -> UTXO[output.hash] = output }
        }
    }
}
# Acoin-Blockchain

Acoin-Blockchain is a simple blockchain implementation in Kotlin. It demonstrates the core concepts of blockchain technology, including wallets, transactions, mining, and the Unspent Transaction Output (UTXO) model.

---

## Features

- **Wallet Creation**: Users can create wallets with public and private keys.
- **Transactions**: Wallets can send funds to each other using signed transactions.
- **Block Mining**: Blocks are mined using Proof-of-Work (PoW) with a configurable difficulty level.
- **UTXO Model**: Tracks unspent transaction outputs to ensure valid transactions.
- **Blockchain Validation**: Ensures the integrity of the blockchain by validating blocks and transactions.

---

## How It Works

1. **Wallets**: Each wallet has a public key (address) and a private key (used for signing transactions).
2. **Transactions**: Transactions are created when a wallet sends funds to another wallet. They include inputs (references to previous transaction outputs) and outputs (new transaction outputs).
3. **Blocks**: Transactions are grouped into blocks. Each block contains a reference to the previous block's hash, forming a chain.
4. **Mining**: Blocks are mined by finding a hash that meets the difficulty requirement (e.g., starts with a certain number of zeros).
5. **UTXO**: The Unspent Transaction Output model ensures that transactions are valid by tracking which outputs have not been spent.

---

## Code Structure

The project is organized into the following files:

- **`Block.kt`**: Defines the `Block` class, which represents a block in the blockchain.
- **`Blockchain.kt`**: Defines the `BlockChain` class, which manages the blockchain and includes mining and validation logic.
- **`Transaction.kt`**: Defines the `Transaction` and `TransactionOutput` classes, which represent transactions and their outputs.
- **`Wallet.kt`**: Defines the `Wallet` class, which allows users to create wallets and send funds.
- **`Utils.kt`**: Contains utility functions for hashing, signing, and verifying signatures.
- **`Main.kt`**: The entry point of the application, demonstrating wallet creation, transactions, and block mining.

---

## Getting Started

### Prerequisites

- Kotlin 1.5 or later
- Java Development Kit (JDK) 8 or later

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Acoin-Blockchain.git

2. Navigate to the project directory:
   ```bash
   cd Acoin-Blockchain

3. Compile the Kotlin files:
   ```bash
   kotlinc *.kt -include-runtime -d AcoinBlockchain.jar

4. Run the program:
   ```bash
   java -jar AcoinBlockchain.jar

## Example Output
-When you run the program, you should see output similar to the following:
   ```bash
   Wallet 1 balance: 0
   Wallet 2 balance: 0
   Mining: Block(...)
   Mined: Block(...)
   Wallet 1 balance: 100
   Wallet 2 balance: 0
   Mining: Block(...)
   Mined: Block(...)
   Wallet 1 balance: 67
   Wallet 2 balance: 33
```
## .gitignore File:
-Add a .gitignore file to exclude unnecessary files from your repository. Here's a basic template for Kotlin/Java projects:
   ```bash
# Compiled class files
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# IntelliJ IDEA files
.idea/
*.iml
*.ipr
*.iws
out/

# Gradle files
.gradle/
build/

# Kotlin files
*.kapt

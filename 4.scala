class Account(val accountNumber: String, initialBalance: Double) {
  private var balance: Double = initialBalance

  def deposit(amount: Double): Unit = {
    require(amount > 0, "Deposit amount must be positive")
    balance += amount
  }

  def withdraw(amount: Double): Unit = {
    require(amount > 0, "Withdrawal amount must be positive")
    if (amount <= balance) {
      balance -= amount
    } else {
      println(s"Insufficient funds in account $accountNumber.")
    }
  }

  def transfer(amount: Double, toAccount: Account): Unit = {
    require(amount > 0, "Transfer amount must be positive")
    if (amount <= balance) {
      this.withdraw(amount)
      toAccount.deposit(amount)
    } else {
      println(s"Insufficient funds in account $accountNumber.")
    }
  }

  def checkBalance: Double = balance

  def applyInterest(): Unit = {
    if (balance > 0) {
      balance += balance * 0.05
    } else {
      balance += balance * 0.1
    }
  }

  override def toString: String = s"Account($accountNumber, Balance: $$${balance})"
}

object Bank {
  type Bank = List[Account]

  def accountsWithNegativeBalances(bank: Bank): List[Account] = {
    bank.filter(_.checkBalance < 0)
  }

  def totalBalance(bank: Bank): Double = {
    bank.map(_.checkBalance).sum
  }

  def applyInterestToAllAccounts(bank: Bank): Bank = {
    bank.foreach(_.applyInterest())
    bank
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val account1 = new Account("12345", 1000.0)
    val account2 = new Account("67890", -500.0)
    val account3 = new Account("11223", 200.0)
    val account4 = new Account("44556", -100.0)

    val bank: Bank.Bank = List(account1, account2, account3, account4)

    println("Accounts with negative balances:")
    Bank.accountsWithNegativeBalances(bank).foreach(println)

    println(s"Total balance of all accounts: $$${Bank.totalBalance(bank)}")

    Bank.applyInterestToAllAccounts(bank)
    println("Final balances after applying interest:")
    bank.foreach(println)
  }
}

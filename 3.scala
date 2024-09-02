class Account(val accountNumber: String, initialBalance: Double) {
  private var balance: Double = initialBalance

 
  def deposit(amount: Double): Unit = {
    require(amount > 0, "Deposit amount must be positive")
    balance += amount
    println(s"Deposited $$${amount} into account $accountNumber. New balance: $$${balance}")
  }

  
  def withdraw(amount: Double): Unit = {
    require(amount > 0, "Withdrawal amount must be positive")
    if (amount <= balance) {
      balance -= amount
      println(s"Withdrew $$${amount} from account $accountNumber. New balance: $$${balance}")
    } else {
      println(s"Insufficient funds in account $accountNumber. Withdrawal of $$${amount} failed.")
    }
  }

  
  def transfer(amount: Double, toAccount: Account): Unit = {
    require(amount > 0, "Transfer amount must be positive")
    if (amount <= balance) {
      this.withdraw(amount)
      toAccount.deposit(amount)
      println(s"Transferred $$${amount} from account $accountNumber to account ${toAccount.accountNumber}.")
    } else {
      println(s"Insufficient funds in account $accountNumber. Transfer of $$${amount} to account ${toAccount.accountNumber} failed.")
    }
  }


  def checkBalance: Double = balance

  override def toString: String = s"Account($accountNumber, Balance: $$${balance})"
}

object Main {
  def main(args: Array[String]): Unit = {
    val account1 = new Account("12345", 1000.0)
    val account2 = new Account("67890", 500.0)

    println(account1)
    println(account2)

    account1.deposit(200.0)          
    account1.withdraw(150.0)         
    account1.transfer(300.0, account2) 

    println(account1) 
    println(account2) 
  }
}

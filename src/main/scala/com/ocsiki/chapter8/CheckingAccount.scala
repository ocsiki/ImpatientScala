package com.ocsiki.chapter8

//1. Extend the following BankAccount class to a CheckingAccount class
// that charges $1 for every deposit and withdrawal.
class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {

  val charge = 1

  var cb: Double = initialBalance

  override def deposit(amount: Double): Double = {
    cb = initialBalance + amount - charge
    cb
  }

  override def withdraw(amount: Double): Double = {
    cb = initialBalance - amount - charge
    cb
  }
}

//2. Extend the BankAccount class of the preceding exercise into a class SavingsAccount that earns interest every month
// (when a method earnMonthlyInterest is called) and has three free deposits or withdrawals every month.
// Reset the transaction count in the earnMonthlyInterest method.
class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {

  val percentageInterest = 1

  var operationCounter = 0
  var cb: Double = initialBalance

  def earnMonthlyInterest(): Unit = {
    cb = cb + cb * (percentageInterest.toDouble/100)
    operationCounter = 0
  }

  override def deposit(amount: Double): Double = {
    if (operationCounter < 3) cb = cb + amount
    else cb = cb + amount - 1

    operationCounter += 1
    cb
  }

  override def withdraw(amount: Double): Double = {
    if (operationCounter < 3) cb = cb - amount
    else cb = cb - amount - 1

    operationCounter += 1
    cb
  }

}

class BankAccount(initialBalance: Double) {

  private var balance = initialBalance

  def currentBalance = balance

  def deposit(amount: Double) = { balance += amount; balance }

  def withdraw(amount: Double) = { balance -= amount; balance }

}



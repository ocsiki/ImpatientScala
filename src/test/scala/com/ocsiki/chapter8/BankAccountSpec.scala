package com.ocsiki.chapter8

import org.scalatest.{FlatSpec, Matchers}

class BankAccountSpec extends FlatSpec with Matchers {

  "A CheckingAccount operation" should "charge $1 for deposit" in {
    val account: CheckingAccount = new CheckingAccount(15.0)
    account.deposit(15)

    assert(account.cb == 29)
  }

  it should "charge $1 for withdrawals" in {
    val account: CheckingAccount = new CheckingAccount(10)
    account.withdraw(9)

    assert(account.cb == 0)
  }

  "A SavingsAccount operation" should "not charge a fee if it's the first one this month" in {
    val account = new SavingsAccount(20)
    account.deposit(15)

    assert(account.cb == 35)
  }

  "A SavingsAccount operation" should "charge a fee if it's the fourth one this month" in {
    val account = new SavingsAccount(20)
    account.deposit(15)
    account.deposit(15)
    account.deposit(15)
    account.deposit(15)

    assert(account.cb == 79)
  }

  it should "not charge a fee if four operation hasve been performed last month" in {
    val account = new SavingsAccount(20)
    account.deposit(15)
    account.deposit(15)
    account.deposit(15)
    account.deposit(15)
    //interest is also applied here
    account.earnMonthlyInterest()
    account.deposit(15)

    //final balance includes last month's interest
    assert(account.cb == 94.79)
  }

  it should "return an interest of 1% every month" in {
    val account = new SavingsAccount(20)
    account.earnMonthlyInterest()

    assert(account.cb == 20.2)
  }




}

package com.ocsiki.chapter10

/*
4. Provide a CryptoLogger trait that encrypts the log messages with the Caesar cipher.
The key should be 3 by default, but it should be overridable by the user.
Provide usage examples with the default key and a key of –3.
 */

trait CryptoLogger {

  val shift: Int = 3

  def log(message: String): String = {

    message.map(c => (c + shift).toChar).toString

  }


}

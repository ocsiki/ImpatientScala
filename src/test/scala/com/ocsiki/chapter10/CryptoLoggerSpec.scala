package com.ocsiki.chapter10

import org.scalatest.{FlatSpec, Matchers}

class CryptoLoggerSpec extends FlatSpec with Matchers {

  "Cryptologger" should "use a cypher with a key of 3 by default" in {
    val m = new MessageGenerator
    m.generateMessageForLogging("abc") shouldBe "def"

  }

}

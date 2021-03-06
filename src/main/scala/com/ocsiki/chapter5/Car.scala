package com.ocsiki.chapter5

class Car(val manufacturer: String, val modelName: String, val modelYear: Int, var licencePlate: String) {

  def this(manufacturer: String, modelName: String) = {
     this(manufacturer, modelName, -1, "")
  }

  def this(manufacturer: String, modelName: String, modelYear: Int) = {
    this(manufacturer, modelName, modelYear, "")
  }

  def this(manufacturer: String, modelName: String, licencePlate: String) = {
    this(manufacturer, modelName, -1, licencePlate)
  }

}

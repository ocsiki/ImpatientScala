package com.ocsiki.chapter5

class Time(val hours: Int, val minutes: Int) {

  require (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59, "Please enter valid values for hours and minutes")

  def before(other: Time) = {
    if (hours < other.hours)
      true
    else if (hours > other.hours)
      false
    else if (minutes < other.minutes)
      true
    else
      false

  }

}

class TimeInMinutes(val hours: Int, val minutes: Int) {
  require (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59, "Please enter valid values for hours and minutes")

  private val minutesSinceMidnight: Int = 60 * hours + minutes

  def before(other: TimeInMinutes): Boolean = {
    this.minutesSinceMidnight < other.minutesSinceMidnight
  }

}

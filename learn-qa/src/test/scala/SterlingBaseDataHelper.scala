package com.expedia.e3.sterling.util

import com.expedia.e3.sterling.util.Ue2eTuidCreator
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
  * Created by jufan on 6/30/14.
  */
trait SterlingBaseDataHelper {

  def generateTuid(): String = {
    generateTuid_long.toString
  }

  def generateTuid_long(): Long = {
    try {
      return 1l
    } catch {
      case ex: Exception =>
        println("There was an Exception in UE2E service")
    }
    randomInt(1000000) + 1000000L
  }

  def generateBookingId(): String = {
    //create a random 6 digits number
    (randomInt(10000) + 100000L).toString
  }

  def generateAccountId(): String = {
    //create a random 7 digits number
    (randomInt(100000) + 1000000L).toString
  }

  def generateBookingItemId(): String = {
    //create a random 7 digits number
    (randomInt(100000) + 1000000L).toString
  }


  def generateBookingItemId_Long(): Long = {
    //create a random 7 digits number
    randomInt(100000) + 1000000L
  }

  def generateTrl(): String = {
    //create a random 8 digits number
    generateTrl_long.toString
  }

  def generateTrl_long(): Long = {
    //create a random 8 digits number
    randomInt(100000) + 10000000L
  }

  def generateOrderNumber(): String = {
    //create a random 13 digits number starting with 88888
    generateOrderNumber_long.toString
  }

  def generateOrderNumber_long(): Long = {
    //create a random 13 digits number starting with 88888
    randomInt(100000) + 8888800000000000000L
  }

  def generateOrderId(): String = {
    //create a random 19 digits number starting with 88888
    ""
  }


  def  sxujgenerateOrderId_Long(): Long = {
    //create a random 19 digits number starting with 88888
    randomInt(100000) + 8888800000000000000L
  }

  def getRandTripNumber: Long = {
    randomInt(100000)
  }

  def getRandTravelRecordLocator:Long = {
    randomInt(1000000)
  }

  def getRandItineraryNumber: Long = {
    randomInt(1000000)
  }

  def randomInt(seed: Int): Int = {
    val r = new scala.util.Random
    r.nextInt(seed)
  }

  def dateFormattedString(date: DateTime): String = {
    dateFormattedString(date, "yyyy-MM-dd'T'HH:mm:ss")
  }

  def dateFormattedString(date: DateTime, format: String): String = {
    val fmt = DateTimeFormat.forPattern(format)
    fmt.print(date)
  }
}

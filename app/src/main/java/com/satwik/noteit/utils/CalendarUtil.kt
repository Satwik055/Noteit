package com.satwik.noteit.utils

import java.text.SimpleDateFormat
import java.util.Calendar

object CalendarUtil {

    //Returns current day of week, eg: Sun

    fun getCurrentDay(): String {
        val sdf = SimpleDateFormat("EEE")
        return sdf.format(Calendar.getInstance().time)
    }

    //Returns current hours and minutes of the day, eg: 01:35
    fun getCurrentTime(): String{
        val sdf = SimpleDateFormat("hh:mm")
        return sdf.format(Calendar.getInstance().time)
    }

    //Returns current date, eg: 6
    fun getCurrentDate(): String{
        val sdf = SimpleDateFormat("dd")
        return sdf.format(Calendar.getInstance().time)
    }

    fun getCurrentMonth(): String{
        val sdf = SimpleDateFormat("MMM")
        return sdf.format(Calendar.getInstance().time)
    }

}
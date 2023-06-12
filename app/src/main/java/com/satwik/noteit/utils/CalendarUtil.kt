package com.satwik.noteit.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object CalendarUtil {

    //Returns current day of week, eg: Sun

    fun getCurrentDay(): String {
        val sdf = SimpleDateFormat("EEE", Locale.getDefault())
        return sdf.format(Calendar.getInstance().time)
    }

    //Returns current hours and minutes of the day, eg: 01:35
    fun getCurrentTime(): String{
        val sdf = SimpleDateFormat("hh:mm", Locale.getDefault())
        return sdf.format(Calendar.getInstance().time)
    }

    //Returns current date, eg: 6
    fun getCurrentDate(): String{
        val sdf = SimpleDateFormat("dd", Locale.getDefault())
        return sdf.format(Calendar.getInstance().time)
    }

    fun getCurrentMonth(): String{
        val sdf = SimpleDateFormat("MMM", Locale.getDefault())
        return sdf.format(Calendar.getInstance().time)
    }

}
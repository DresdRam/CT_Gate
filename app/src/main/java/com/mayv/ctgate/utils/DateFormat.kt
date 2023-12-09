package com.mayv.ctgate.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormat {

    fun reformatLogDate(date: String): String {
        val newDate = date.replace("T", " ").replace("Z", "")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss", Locale.getDefault())
        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val day = getDayName(dayFormat.format(dateFormat.parse(newDate)!!))
        val finalFormat = SimpleDateFormat("يوم $day الموافق yyyy-MM-dd الساعة HH:mm", Locale.getDefault())
        return finalFormat.format(dateFormat.parse(newDate)!!)
    }

    fun reformatDate(date: String): String {
        val newDate = date.replace("T", " ").replace("Z", "")
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormatter.format(dateFormatter.parse(newDate)!!)
    }

    private fun getLogDateInfo(day: String, time: String = ""): String {
        val stringBuilder = StringBuilder("يوم")
        stringBuilder.append(" ")
        stringBuilder.append(getDayName(day))
        stringBuilder.append(" ")
        stringBuilder.append("الموافق")
        stringBuilder.append(" ")
        stringBuilder.append("2023-12-05")
        stringBuilder.append(" ")
        stringBuilder.append("الساعة")
        stringBuilder.append(" ")
        stringBuilder.append(time)
        return stringBuilder.toString()
    }

    private fun getDayName(day: String): String {
        return when (day) {
            "Saturday" -> {
                "السبت"
            }

            "Sunday" -> {
                "الاحد"
            }

            "Monday" -> {
                "الاثنين"
            }

            "Tuesday" -> {
                "الثلاثاء"
            }

            "Wednesday" -> {
                "الاربعاء"
            }

            "Thursday" -> {
                "الخميس"
            }

            "Friday" -> {
                "الجمعة"
            }

            else -> {
                "السبت"
            }
        }
    }

}
package com.mayv.ctgate.utils

import java.text.SimpleDateFormat

fun reformatDate(date: String, type: DateType = DateType.LogDate): String {
    val newDate = date.replace("T", " ").replace("Z", "")
    val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
    val formatter = if(type == DateType.LogDate) SimpleDateFormat("yyyy-MM-dd <-> HH:mm") else SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(parser.parse(newDate))
}
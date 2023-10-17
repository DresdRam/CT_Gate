package com.mayv.ctgate.model

data class Enrollment(
    val enrollment_date: String = "",
    val holiday_group: String = "",
    val police_number: Int = 0,
    val unit_job: String = "",
    val unit: Unit = Unit()
)
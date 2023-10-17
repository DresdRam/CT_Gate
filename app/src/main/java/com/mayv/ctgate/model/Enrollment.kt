package com.mayv.ctgate.model

data class Enrollment(
    val agenda_id: Int = 0,
    val enrollment_date: String = "",
    val enrollment_status: String = "",
    val holiday_group: String = "",
    val id: Int = 0,
    val join_camp_date: String = "",
    val police_number: Int = 0,
    val quit_camp_date: String = "",
    val street_status: String = "",
    val unit: Unit = Unit(),
    val unit_enrollment_date: String = "",
    val unit_job: String = "",
    val unit_side_job: String = ""
)
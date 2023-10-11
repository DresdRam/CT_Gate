package com.mayv.ctgate.model

data class GateLog(
    val date: String,
    val id: String,
    val overtime: Int,
    val section: String,
    val soldier_id: String,
    val sub_type: Int,
    val time_section: String,
    val type: String
)
package com.mayv.ctgate.model

data class GateLog(
    val id: Int = 0,
    val date: String = "",
    val overtime: Boolean = false,
    val section: String = "",
    val service_location: String = "",
    val sub_type: Int = 0,
    val time_section: String = "",
    val type: String = "",
    val soldier: Soldier = Soldier()
)
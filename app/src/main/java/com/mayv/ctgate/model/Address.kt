package com.mayv.ctgate.model

data class Address(
    val city: String = "",
    val governorate: Governorate = Governorate(),
    val id: Int = 0,
    val street: String = ""
)
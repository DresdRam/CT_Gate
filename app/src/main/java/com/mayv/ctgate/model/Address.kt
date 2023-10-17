package com.mayv.ctgate.model

data class Address(
    val city: String,
    val governorate: Governorate,
    val id: Int,
    val street: String
)
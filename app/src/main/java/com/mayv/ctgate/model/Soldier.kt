package com.mayv.ctgate.model

data class Soldier(
    val address: List<Address> = listOf(),
    val birth_date: String = "",
    val card_id: Int = 0,
    val education: String = "",
    val enrollment: Enrollment = Enrollment(),
    val enrollment_id: Int = 0,
    val id: Int = 0,
    val job: String = "",
    val medical_condition: String = "",
    val medical_condition_type: String = "",
    val name: String = "",
    val national_id: Long = 0,
    val phoneNumbers: List<PhoneNumber> = listOf(),
    val phone_number: String = "",
    val qualification: String = "",
    val rating: String = "",
    val rating_status: Boolean = false,
    val rating_type: String = "",
    val religion: String = "",
    val removed: Boolean = false,
    val status: Int = 0,
    val triple_digit_number: String = ""
)
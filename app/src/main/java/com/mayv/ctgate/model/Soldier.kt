package com.mayv.ctgate.model

data class Soldier(
    val address: List<Addres>,
    val birth_date: String,
    val card_id: Any,
    val education: String,
    val enrollment: Enrollment,
    val enrollment_id: Int,
    val id: Int,
    val job: String,
    val medical_condition: String,
    val medical_condition_type: String,
    val name: String,
    val national_id: Long,
    val phoneNumbers: List<PhoneNumber>,
    val phone_number: String,
    val qualification: String,
    val rating: String,
    val rating_status: Boolean,
    val rating_type: String,
    val religion: String,
    val removed: Boolean,
    val status: Int,
    val triple_digit_number: String
)
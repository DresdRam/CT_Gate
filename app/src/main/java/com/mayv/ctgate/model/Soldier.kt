package com.mayv.ctgate.model

data class Soldier(
    val id: Int = 0,
    val name: String = "",
    val national_id: Long = 0,
    val qualification: String = "",
    val phone_number: String = "",
    val rating: String = "",
    val rating_status: Boolean = false,
    val rating_type: String = "",
    val medical_condition: String = "",
    val medical_condition_type: String = "",
    val removed: Boolean = false,
    val address: List<Address> = listOf(),
    val notes: List<Note> = listOf(),
    val enrollment: Enrollment = Enrollment(),
)
{
    fun getNotesAsString(): String{
        val builder: StringBuilder = StringBuilder()

        notes.forEach { note ->
            builder.append(note.note).append("\n")
        }

        return builder.toString()
    }
}
package com.mayv.ctgate.utils.funs

fun getGateSubtype(subtype: Int): String {
    return when (subtype) {
        0 -> "فسحة"
        1 -> "منحة"
        2 -> "اجازة"
        3 -> "مامورية"
        4 -> "خدمة"
        5 -> "مستشفى"
        6 -> "غير ذلك"
        7 -> "تعيين"
        8 -> "دورية"
        9 -> "تشريفة"
        else -> "غير ذلك"
    }
}
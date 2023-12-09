package com.mayv.ctgate.utils.funs

fun getExceptionFromStatusCode(statusCode: Int): Exception{
    return when (statusCode) {
        401 -> Exception("! خطأ في معلومات تسجيل الدخول")
        403 -> Exception("! انت غير مصرح بك")
        500 -> Exception("! خطأ في الخادم")
        400, 404 -> Exception("! لا يوجد اتصال بالخادم")
        else -> Exception("! فشل العملية")
    }
}
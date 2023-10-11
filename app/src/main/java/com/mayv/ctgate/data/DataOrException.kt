package com.mayv.ctgate.data

class DataOrException<Template, Boolean, Exception>(
    var data: Template? = null,
    var loading: Boolean? = null,
    var exception: Exception? = null
)
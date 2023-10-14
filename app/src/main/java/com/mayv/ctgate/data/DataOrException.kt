package com.mayv.ctgate.data

class DataOrException<Template, Loading, Failed, Exception>(
    var data: Template? = null,
    var loading: Loading? = null,
    var failed: Failed? = null,
    var exception: Exception? = null
)
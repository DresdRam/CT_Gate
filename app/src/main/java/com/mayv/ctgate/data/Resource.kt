package com.mayv.ctgate.data

class Resource<Template>(
    var data: Template? = null,
    var loading: Boolean = true,
    var failed: Boolean = false,
    var exception: Exception? = null
)
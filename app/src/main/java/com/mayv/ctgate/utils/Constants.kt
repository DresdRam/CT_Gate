package com.mayv.ctgate.utils

object Constants {

    private const val IP = "192.168.1.60"

    private const val PORT = 3026

    const val BASE_URL = "http://$IP:$PORT/"

    fun getIp() = IP
    fun getPort() = PORT

}
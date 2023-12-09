package com.mayv.ctgate.utils

object Constants {

    private const val IP = "192.168.1.60"

    private const val PORT = 3026

    private const val AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiR0FURSJ9.0e4syE4wkM6zkW457DdyPl7fzRSesSyYQ9ezTDPTNHs"

    const val BASE_URL = "http://$IP:$PORT/"

    fun getIp() = IP
    fun getPort() = PORT
    fun getAuthToken() = AUTH_TOKEN
}
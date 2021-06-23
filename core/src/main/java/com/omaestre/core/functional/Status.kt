package com.omaestre.core.functional

sealed class Status<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : Status<T>()
    class Success<T>(data: T) : Status<T>(data)
    class Error<T> : Status<T>()
}
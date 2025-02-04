package com.esoapps.domain.ext


sealed interface Result<out R> {
    data object Loading : Result<Nothing>
    data class Success<out T>(val data: T) : Result<T>
    data class Error<out T>(val message: String ) : Result<T>
}

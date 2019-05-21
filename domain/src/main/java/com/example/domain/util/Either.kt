package com.example.domain.util

sealed class Either<out E, out S> {
    //data classes to handle Error and Success, returns either an error or the Data with a nothing type for the other
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Success<out S>(val data: S) : Either<Nothing, S>()

    val isError
        get() = this is Error<E>

    val isSuccess
        get() = this is Success<S>

    fun <E> error(error: E) = Error(error)
    fun <S> success(data: S) = Success(data)

    fun either(onError: (E) -> Any, onSuccess: (S) -> Any): Any =
            when(this){
                is Error -> onError(error)
                is Success -> onSuccess(data)
            }
}

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
}
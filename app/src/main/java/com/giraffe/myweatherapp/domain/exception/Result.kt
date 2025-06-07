package com.giraffe.myweatherapp.domain.exception


sealed interface Result<out D, out E : DomainError> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : DomainError>(val error: E) : Result<Nothing, E>
}

inline fun <D, E : DomainError, T> Result<D, E>.map(map: (D) -> T): Result<T, E> {
    return when (this) {
        is Result.Success -> Result.Success(map(data))
        is Result.Error -> Result.Error(error)
    }
}

inline fun <D, E : DomainError> Result<D, E>.onSuccess(action: (D) -> Unit): Result<D, E> {
    return when (this) {
        is Result.Success -> {
            action(data)
            this
        }

        is Result.Error -> this
    }
}

inline fun <D, E : DomainError> Result<D, E>.onError(action: (E) -> Unit): Result<D, E> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }
}
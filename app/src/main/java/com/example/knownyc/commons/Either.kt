package com.example.knownyc.commons



data class AppError(
  val message : String,
  val throwable: Throwable? = null,
)

sealed class Either<out AppError, out T> {
    class Error<AppError>(val error: AppError) : Either<AppError, Nothing>()
    class Data<T>(val value: T) : Either<Nothing, T>()
}
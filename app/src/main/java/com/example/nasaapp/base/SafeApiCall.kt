package com.example.nasaapp.base

import retrofit2.HttpException

interface SafeApiCall {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ApiResponse<T> {
        return try {
            ApiResponse.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    ApiResponse.Failure(
                        false,
                        throwable.code(), throwable.response()?.errorBody()
                    )
                }
                else -> {
                    ApiResponse.Failure(true, null, null)
                }
            }
        }
    }


}
package com.example.nasaapp.data.repository

import android.util.Log
import com.example.nasaapp.base.ApiResponse
import com.example.nasaapp.base.SafeApiCall
import com.example.nasaapp.data.service.NasaDataService
import com.example.nasaapp.domain.entity.NasaDataEntity
import javax.inject.Inject

class NasaRepository @Inject constructor(private val nasaDataService: NasaDataService) :
    SafeApiCall {

    sealed class LocalException(
        val title: String,
        val description: String = ""
    ) : Exception() {
        object EmptySearch : LocalException(
            title = "Start typing to search!"
        )

        object NoResults : LocalException(
            title = "Whoops!",
            description = "Looks like your search returned no results"
        )
    }


    suspend fun getNasaData(): NasaDataEntity? {
        val response = safeApiCall {
            nasaDataService.fetchAllData()
        }

        var emptyList: NasaDataEntity? = null

        if (response is ApiResponse.Success) {
            return response.value
        }

        if (response is ApiResponse.Failure) {
            val v = response.errorBody
            try {
                if (response.errorCode == 404) {
                    val exception = LocalException.NoResults
                    throw (exception)
                }
            } catch (e: java.lang.Exception) {
                Log.d("TAG", "getNasaData: $e")
            }

            emptyList = NasaDataEntity()
        }

        return emptyList
    }


}
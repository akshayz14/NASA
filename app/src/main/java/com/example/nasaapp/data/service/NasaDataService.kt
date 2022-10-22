package com.example.nasaapp.data.service

import com.example.nasaapp.base.ApiResponse
import com.example.nasaapp.domain.entity.NasaDataEntity
import retrofit2.http.GET

interface NasaDataService {

    @GET("api/nasa/data")
    suspend fun fetchAllData(): NasaDataEntity


}
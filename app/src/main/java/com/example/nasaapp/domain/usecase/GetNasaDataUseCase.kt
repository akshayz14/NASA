package com.example.nasaapp.domain.usecase

import com.example.nasaapp.data.repository.NasaRepository
import com.example.nasaapp.domain.entity.NasaDataEntity
import javax.inject.Inject

class GetNasaDataUseCase @Inject constructor(private val repository: NasaRepository) {
    suspend fun execute(): NasaDataEntity? {
        return repository.getNasaData()
    }
}
package com.example.nasaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapp.domain.entity.NasaDataEntity
import com.example.nasaapp.domain.entity.NasaDataEntityItem
import com.example.nasaapp.domain.usecase.GetNasaDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NasaViewModel @Inject constructor(private val getNasaDataUseCase: GetNasaDataUseCase) :
    ViewModel() {

    private val _nasaLiveData =
        MutableLiveData<NasaDataEntity?>()
    val nasaLiveData: LiveData<NasaDataEntity?> =
        _nasaLiveData

    private val _updatedNasaLiveData =
        MutableLiveData<List<NasaDataEntityItem>>()
    val updatedNasaLiveData: LiveData<List<NasaDataEntityItem>> =
        _updatedNasaLiveData

    private var updatedList: List<NasaDataEntityItem> = mutableListOf()

    init {
        getNasaData()
    }

    private fun getNasaData() {
        viewModelScope.launch {
            _nasaLiveData.value = getNasaDataUseCase.execute()
        }
    }

    fun setNasaData(list: MutableList<NasaDataEntityItem>) {
        updatedList = list
    }

    fun getUpdatedNasaData() {
        _updatedNasaLiveData.value = updatedList
    }

}
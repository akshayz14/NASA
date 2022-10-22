package com.example.nasaapp.model

class NasaDataModel : ArrayList<NasaDataItem>()


data class NasaDataItem(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)



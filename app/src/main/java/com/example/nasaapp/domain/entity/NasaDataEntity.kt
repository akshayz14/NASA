package com.example.nasaapp.domain.entity

import androidx.annotation.NonNull
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class NasaDataEntity : ArrayList<NasaDataEntityItem>()


data class NasaDataEntityItem(


    var id: String,

    @SerializedName("copyright")
    val copyright: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("explanation")
    val explanation: String,

    @SerializedName("hdurl")
    val hdurl: String,

    @SerializedName("media_type")
    val media_type: String,

    @SerializedName("service_version")
    val service_version: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String
)
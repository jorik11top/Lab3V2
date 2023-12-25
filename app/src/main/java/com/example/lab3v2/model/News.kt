package com.example.lab3v2.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)

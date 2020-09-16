package com.example.orenda.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoModel(
    val albumId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

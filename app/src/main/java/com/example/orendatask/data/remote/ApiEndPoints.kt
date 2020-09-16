package com.example.orendatask.data.remote

import com.example.orenda.model.PhotoModel
import retrofit2.Call
import retrofit2.http.GET


interface APIService {
    @GET("/photos")
    fun getPhotos() : Call<List<PhotoModel>>
}


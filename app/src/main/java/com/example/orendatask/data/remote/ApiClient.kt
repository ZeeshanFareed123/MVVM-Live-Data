package com.example.orendatask.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {
        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        private var apiService: APIService = retrofit.create(
            APIService::class.java)

        fun getRetrofitClient(): APIService {
            return apiService
        }
    }

}


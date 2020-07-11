package com.example.projectmagang.api

class UtilsAPI {
    val BASE_ROOT_URL = "http://192.168.43.46/TryoutOnline/public/api/"
    val apiService:BaseApiService
        get() {
            return RetrofitClient().getClient(BASE_ROOT_URL)!!.create(BaseApiService::class.java)
        }
}
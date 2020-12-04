package com.jobplanet.test.network


import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("mobile-config/test_data.json")
    fun getTestData() : Call<ArrayList<Any>>
}
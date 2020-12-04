package com.jobplanet.test.network

import com.jobplanet.test.util.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitModule {

    private lateinit var instance : RetrofitModule

    private fun retrofit():Retrofit {
        val BASE_URL = " https://jpassets.jobplanet.co.kr/"
        return Retrofit.Builder()
            .addConverterFactory(JsonParser.getCustomConverter())
            .client(OkHttpClient.Builder().apply {
                connectTimeout(40, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)}
                .build())
            .baseUrl(BASE_URL)
            .build()
    }

    fun getInstance() : ApiService {
        return retrofit().create(ApiService::class.java)
    }

}
package com.lukman.digiquran.services

import com.lukman.digiquran.Ayat
import com.lukman.digiquran.Surat
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitServices {
    @GET("surat")
    fun getAllSurat(): Call<List<Surat>>

    @GET("surat/{nomor}")
    fun getAllAyat(@Path("nomor") nomor:Int): Call<Ayat>

    companion object {
        var retrofitService: RetrofitServices? = null

        fun getInstance(): RetrofitServices {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://equran.id/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitServices::class.java)
            }
            return retrofitService!!
        }
    }
}
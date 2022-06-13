package com.lukman.digiquran.repository

import com.lukman.digiquran.services.RetrofitServices

class MainRepository constructor(private val retrofitService: RetrofitServices) {
    fun getAllSurat() = retrofitService.getAllSurat()
    fun getAllAyat(no:Int) = retrofitService.getAllAyat(no)
}
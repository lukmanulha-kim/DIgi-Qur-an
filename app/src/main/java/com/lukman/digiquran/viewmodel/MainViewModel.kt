package com.lukman.digiquran.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukman.digiquran.Ayat
import com.lukman.digiquran.Surat
import com.lukman.digiquran.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val suratList = MutableLiveData<List<Surat>>()
    val ayatList = MutableLiveData<Ayat>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllSurat()
        response.enqueue(object : Callback<List<Surat>> {
            override fun onResponse(call: Call<List<Surat>>, response: Response<List<Surat>>) {
                suratList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Surat>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getAllAyat(nomor: Int) {
        val response = repository.getAllAyat(nomor)
        response.enqueue(object : Callback<Ayat> {
            override fun onResponse(call: Call<Ayat>, response: Response<Ayat>) {
                ayatList.postValue(response.body())
            }
            override fun onFailure(call: Call<Ayat>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
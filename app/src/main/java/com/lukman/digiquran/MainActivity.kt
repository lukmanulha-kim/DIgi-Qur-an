package com.lukman.digiquran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lukman.digiquran.databinding.ActivityMainBinding
import com.lukman.digiquran.model.MyViewModelFactory
import com.lukman.digiquran.repository.MainRepository
import com.lukman.digiquran.services.RetrofitServices
import com.lukman.digiquran.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitServices.getInstance()
    val adapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)
        binding.recyclerview.adapter = adapter
        viewModel.suratList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setSuratList(it)
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getAllMovies()
    }
}
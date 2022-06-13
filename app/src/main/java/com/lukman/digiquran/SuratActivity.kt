package com.lukman.digiquran

import android.content.ContentValues.TAG
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lukman.digiquran.adapter.AyatAdapter
import com.lukman.digiquran.databinding.ActivitySuratBinding
import com.lukman.digiquran.model.MyViewModelFactory
import com.lukman.digiquran.repository.MainRepository
import com.lukman.digiquran.services.RetrofitServices
import com.lukman.digiquran.viewmodel.MainViewModel
import java.io.IOException

class SuratActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuratBinding

    private lateinit var cvNamaSurat : CardView

    var mediaPlayer : MediaPlayer?=null

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitServices.getInstance()
    val adapter = AyatAdapter()

    companion object {
        const val EXTRA_NOMOR = "extra_nomor"
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_NAMALATIN = "extra_nama_latin"
        const val EXTRA_JUMLAHAYAT = "extra_jumlah_ayat"
        const val EXTRA_TPTTURUN = "extra_tempat_turun"
        const val EXTRA_ARTI = "extra_arti"
        const val EXTRA_DEKRIPSI = "extra_deskripsi"
        const val EXTRA_AUDIO = "extra_audio"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat)
        val actionbar = supportActionBar
        actionbar!!.title = intent.getStringExtra(EXTRA_NAMALATIN)
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding = ActivitySuratBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)
        binding.rvAyat.adapter = adapter
        viewModel.ayatList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            if (!it.ayat.isNullOrEmpty())
            adapter.setAyatList(it.ayat)
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getAllAyat(intent.getIntExtra(EXTRA_NOMOR,0))

        cvNamaSurat = findViewById(R.id.cvNamaSurat)

        cvNamaSurat.setOnClickListener {
            playAudio()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun playAudio() {
        val audioUrl = intent.getStringExtra(EXTRA_AUDIO)

        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioAttributes(AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build())
        try {
            mediaPlayer!!.setDataSource(audioUrl)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    private fun stopAudio() {
        if (mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
        }
    }
}

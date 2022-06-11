package com.lukman.digiquran

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.io.IOException

class SuratActivity : AppCompatActivity() {

    private lateinit var cvNamaSurat : CardView

    var mediaPlayer : MediaPlayer?=null

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

        cvNamaSurat = findViewById(R.id.cvNamaSurat)

        cvNamaSurat.setOnClickListener {
            playAudio()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        stopAudio()
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

package com.lukman.digiquran

import com.lukman.digiquran.model.AyatX

data class Surat(
    val nomor: Int,
    val nama: String,
    val nama_latin: String,
    val jumlah_ayat: Int,
    val tempat_turun: String,
    val arti: String,
    val deskripsi: String,
    val audio:String
)

data class Ayat(
    val arti: String?,
    val audio: String?,
    val ayat: List<AyatX>?,
    val deskripsi: String?,
    val jumlah_ayat: Int?,
    val nama: String?,
    val nama_latin: String?,
    val nomor: Int?,
    val status: Boolean?,
    val tempat_turun: String?
)

data class AyatX(
    val ar: String?,
    val id: Int?,
    val idn: String?,
    val nomor: Int?,
    val surah: Int?,
    val tr: String?
)

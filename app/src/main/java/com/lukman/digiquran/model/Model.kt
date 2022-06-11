package com.lukman.digiquran

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

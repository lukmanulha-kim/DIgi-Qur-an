package com.lukman.digiquran.model

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
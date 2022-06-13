package com.lukman.digiquran.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukman.digiquran.Ayat
import com.lukman.digiquran.model.AyatX
import com.lukman.digiquran.Surat
import com.lukman.digiquran.SuratActivity
import com.lukman.digiquran.databinding.AdapterAyatBinding
import com.lukman.digiquran.databinding.AdapterSuratBinding

class AyatAdapter: RecyclerView.Adapter<AyatViewHolder>() {
    var ayat = mutableListOf<AyatX>()
    fun setAyatList(ayatX: List<AyatX>) {
        this.ayat = ayatX.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterAyatBinding.inflate(inflater, parent, false)
        return AyatViewHolder(binding)
    }
    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val ayats = ayat[position]
        holder.binding.ayatArab.text = ayats.ar
        holder.binding.translate.text = ayats.idn
    }
    override fun getItemCount(): Int {
        return ayat.size
    }
}

class AyatViewHolder(val binding: AdapterAyatBinding) : RecyclerView.ViewHolder(binding.root) {
}
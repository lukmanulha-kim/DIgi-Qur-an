package com.lukman.digiquran

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukman.digiquran.databinding.AdapterSuratBinding

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var surats = mutableListOf<Surat>()
    fun setSuratList(surats: List<Surat>) {
        this.surats = surats.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterSuratBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val surat = surats[position]
        holder.binding.namaSuratIdn.text = surat.nama_latin
        holder.binding.namaSuratArab.text = surat.nama

        val mContext = holder.itemView.context

        holder.binding.cvListSurat.setOnClickListener {
            val intent = Intent(mContext, SuratActivity::class.java).apply {
                putExtra(SuratActivity.EXTRA_NAMALATIN, surat.nama_latin)
                putExtra(SuratActivity.EXTRA_NAMA, surat.nama)
                putExtra(SuratActivity.EXTRA_NOMOR, surat.nomor)
                putExtra(SuratActivity.EXTRA_DEKRIPSI, surat.deskripsi)
                putExtra(SuratActivity.EXTRA_ARTI, surat.arti)
                putExtra(SuratActivity.EXTRA_TPTTURUN, surat.tempat_turun)
                putExtra(SuratActivity.EXTRA_JUMLAHAYAT, surat.jumlah_ayat)
                putExtra(SuratActivity.EXTRA_AUDIO, surat.audio)
            }
            mContext.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return surats.size
    }
}

class MainViewHolder(val binding: AdapterSuratBinding) : RecyclerView.ViewHolder(binding.root) {
}
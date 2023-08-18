package com.technipixl.exo1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.CellBinding
import com.technipixl.exo1.network.Character

class CharacterAdapter(private var data: List<Character>, val onItemClick: (Character)->Unit) : RecyclerView.Adapter<CharacterViewHolder> () {
    private lateinit var binding: CellBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = CellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(data[position])
    }

}
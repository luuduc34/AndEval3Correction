package com.technipixl.exo1.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.CellComicsBinding
import com.technipixl.exo1.network.comics.ItemList

class ComicsAdapter(private var data: List<ItemList>, val onItemClick: (ItemList)->Unit) : RecyclerView.Adapter<ComicsViewHolder> (){
    private lateinit var binding: CellComicsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        binding = CellComicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsViewHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
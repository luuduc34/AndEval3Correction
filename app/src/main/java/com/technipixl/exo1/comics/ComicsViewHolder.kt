package com.technipixl.exo1.comics

import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.CellComicsBinding
import com.technipixl.exo1.network.comics.ItemList

class ComicsViewHolder(private var viewBinding: CellComicsBinding, val onItemClick: (ItemList)->Unit) : RecyclerView.ViewHolder(viewBinding.root){

    fun bind(itemList: ItemList) {
        viewBinding.comicsName.text = itemList.name
        viewBinding.comicsContainer.setOnClickListener {
            onItemClick(itemList)
        }
    }
}
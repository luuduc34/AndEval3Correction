package com.technipixl.exo1

import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.CellComicsBinding
import com.technipixl.exo1.network.ItemList

class ComicsViewHolder(private var viewBinding: CellComicsBinding) : RecyclerView.ViewHolder(viewBinding.root){

    fun bind(itemList: ItemList) {
        viewBinding.comicsName.text = itemList.name
    }
}
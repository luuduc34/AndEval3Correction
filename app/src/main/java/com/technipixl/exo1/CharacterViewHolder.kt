package com.technipixl.exo1


import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.technipixl.exo1.databinding.CellBinding
import com.technipixl.exo1.network.Character

class CharacterViewHolder(private var viewBinding: CellBinding, val onItemClick: (Character)->Unit) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(character: Character) {
       setupImage(character.thumbnail.path + "." + character.thumbnail.extension)
        viewBinding.charName.text = character.name

        viewBinding.container.setOnClickListener {
            onItemClick(character)
        }
    }
    private fun setupImage(url: String) {
        Picasso.get()
            .load(url)
            .fit()
            .centerCrop()
            .into(viewBinding.charImageView)
    }
}

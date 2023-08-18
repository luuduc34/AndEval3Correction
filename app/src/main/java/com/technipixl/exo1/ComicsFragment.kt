package com.technipixl.exo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.technipixl.exo1.databinding.FragmentComicsBinding
import com.technipixl.exo1.network.ItemList
import com.technipixl.exo1.network.MarvelServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicsFragment : Fragment() {

    private var binding: FragmentComicsBinding? = null
    private val args: ComicsFragmentArgs by navArgs()
    private val MarvelService by lazy { MarvelServiceImpl() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComicsBinding.inflate(layoutInflater, container, false)
        getCharacterAsync()
        return binding?.root
    }

    private fun getCharacterAsync() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = MarvelService.getCharacterDetail(args.characterId)
            withContext(Dispatchers.Main) {

                val comicsDetail = response.body()?.data?.results
                // Affichage nom
                binding?.titleView?.text = comicsDetail?.get(0)?.name
                // Affichage image
                val thumb = comicsDetail?.get(0)?.thumbnail
                setupImage(thumb?.path + "." + thumb?.extension)

                val comicsList = response.body()?.data?.results?.get(0)?.comics?.items ?: emptyList()
                setupRecyclerView(comicsList)
            }
        }
    }
    private fun setupRecyclerView(itemList: List<ItemList>) {
        val recyclerView = binding?.comicsRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView?.adapter = ComicsAdapter(itemList)
    }
    private fun setupImage(url: String) {
        Picasso.get()
            .load(url)
            .fit()
            .centerCrop()
            .into(binding?.titleImageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
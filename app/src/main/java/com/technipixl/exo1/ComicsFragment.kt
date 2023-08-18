package com.technipixl.exo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.technipixl.exo1.databinding.FragmentComicsBinding
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
                val characterDetail = response.body()?.data?.results
                binding?.titleView?.text = characterDetail?.get(0)?.name
                characterDetail?.get(0)?.thumbnail?.let { setupImage(it.path + "." + it.extension) }
            }
        }
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
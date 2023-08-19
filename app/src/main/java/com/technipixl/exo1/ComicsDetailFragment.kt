package com.technipixl.exo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.technipixl.exo1.databinding.FragmentComicsDetailBinding
import com.technipixl.exo1.network.MarvelServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicsDetailFragment : Fragment() {

    private var binding: FragmentComicsDetailBinding? = null
    private val args: ComicsDetailFragmentArgs by navArgs()
    private val MarvelService by lazy { MarvelServiceImpl() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComicsDetailBinding.inflate(layoutInflater, container, false)
        getComicsCharacterAsync()
        binding?.backBtn?.setOnClickListener{
            findNavController().navigateUp()
        }
        return binding?.root
    }

    private fun getComicsCharacterAsync() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = MarvelService.getComicsCharacterDetail(args.comicsId.substringAfterLast("/"))
            withContext(Dispatchers.Main) {

                val comicsDetail = response.body()?.data?.results
                // Affichage nom
                binding?.titleView?.text = comicsDetail?.get(0)?.title
                // Affichage image
                val thumb = comicsDetail?.get(0)?.images?.get(0)
                setupImage(thumb?.path + "." + thumb?.extension)

                val description = response.body()?.data?.results?.get(0)?.description
                binding?.descriptionView?.text = description
            }
        }
    }

    private fun setupImage(url: String) {
        Picasso.get()
            .load(url)
            .fit()
            .centerInside()
            .into(binding?.titleImageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
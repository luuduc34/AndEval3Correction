package com.technipixl.exo1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.FragmentCharactersBinding
import com.technipixl.exo1.network.Character
import com.technipixl.exo1.network.MarvelServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersFragment : Fragment() {

    private var binding: FragmentCharactersBinding? = null

    private val MarvelService by lazy { MarvelServiceImpl() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        getCharacterAsync()
        return binding?.root

    }
    private fun getCharacterAsync() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = MarvelService.getCharacter()
            withContext(Dispatchers.Main) {
                val characterList = response.body()?.data?.results ?: emptyList()
                setupRecyclerView(characterList)

            }
        }
    }
    private fun setupRecyclerView(characterList: List<Character>) {
        val recyclerView = binding?.marvelRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView?.adapter = CharacterAdapter(characterList){
                character -> goToDetail(character)

        }
    }

    private fun goToDetail(character: Character) {
        val direction = CharactersFragmentDirections.actionCharactersFragmentToComicsFragment((character.id).toLong())
        findNavController().navigate(direction)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
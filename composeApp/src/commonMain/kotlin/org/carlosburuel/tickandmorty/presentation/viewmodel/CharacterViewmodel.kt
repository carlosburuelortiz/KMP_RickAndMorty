package org.carlosburuel.tickandmorty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.carlosburuel.tickandmorty.domain.repository.CharacterRepository

class CharacterViewmodel(
    private val characterRepository: CharacterRepository,
) : ViewModel() {
    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            val characters = characterRepository.getCharacters(1)
            println(characters)
        }
    }
}

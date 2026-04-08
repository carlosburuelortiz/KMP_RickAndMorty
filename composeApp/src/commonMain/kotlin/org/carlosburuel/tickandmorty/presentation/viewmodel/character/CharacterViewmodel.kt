package org.carlosburuel.tickandmorty.presentation.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosburuel.tickandmorty.domain.repository.CharacterRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class CharacterViewmodel(
    private val characterRepository: CharacterRepository,
) : ViewModel(), ContainerHost<CharacterState, CharacterSideEffect> {

    override val container =
        viewModelScope.container<CharacterState, CharacterSideEffect>(CharacterState())

    init {
        getCharacters()
    }

    private fun getCharacters() = intent {
        reduce { state.copy(isLoading = true) }
        val characters = characterRepository.getCharacters(1)
        reduce {
            state.copy(
                isLoading = false,
                characters = characters,
            )
        }
    }
}

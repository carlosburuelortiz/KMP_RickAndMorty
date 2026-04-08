package org.carlosburuel.tickandmorty.presentation.viewmodel.character

import org.carlosburuel.tickandmorty.domain.model.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
)

package org.carlosburuel.tickandmorty.presentation.screen.character.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.carlosburuel.tickandmorty.domain.model.Character
import org.carlosburuel.tickandmorty.presentation.viewmodel.character.CharacterState

class   CharacterStateParameterProvider : PreviewParameterProvider<CharacterState> {
    override val values: Sequence<CharacterState> = sequenceOf(
        CharacterState(
            isLoading = true,
            characters = emptyList(),
        ),
        CharacterState(
            isLoading = false,
            characters = listOf(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Humano",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = "",
                ),
                Character(
                    id = 2,
                    name = "Rick Smith",
                    status = "Alive",
                    species = "Humano",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = "",
                )
            ),
        ),
    )
}

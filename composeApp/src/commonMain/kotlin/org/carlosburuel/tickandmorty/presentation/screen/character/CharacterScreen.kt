package org.carlosburuel.tickandmorty.presentation.screen.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosburuel.tickandmorty.presentation.screen.character.content.CharacterItem
import org.carlosburuel.tickandmorty.presentation.viewmodel.character.CharacterViewmodel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterScreen() {
    val viewModel = koinViewModel<CharacterViewmodel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (state.characters.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = state.characters,
                    key = { it.id },
                ) { character ->
                    CharacterItem(character = character)
                }
            }
        }
    }
}



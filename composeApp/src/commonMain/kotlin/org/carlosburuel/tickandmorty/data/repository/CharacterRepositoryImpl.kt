package org.carlosburuel.tickandmorty.data.repository

import org.carlosburuel.tickandmorty.data.mapper.toCharacter
import org.carlosburuel.tickandmorty.data.remote.RickAndMortyApi
import org.carlosburuel.tickandmorty.domain.model.Character
import org.carlosburuel.tickandmorty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<Character> =
        api.getCharacters(page).results.map {
            it.toCharacter()
        }
}

package org.carlosburuel.tickandmorty.domain.repository

import org.carlosburuel.tickandmorty.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int): List<Character>
}

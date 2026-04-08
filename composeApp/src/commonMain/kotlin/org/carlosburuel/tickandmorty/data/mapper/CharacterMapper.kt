package org.carlosburuel.tickandmorty.data.mapper

import org.carlosburuel.tickandmorty.data.model.CharacterResponse
import org.carlosburuel.tickandmorty.domain.model.Character

fun CharacterResponse.CharacterData.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        location = location.name,
        image = image,
    )
}

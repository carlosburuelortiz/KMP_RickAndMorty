package org.carlosburuel.tickandmorty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.carlosburuel.tickandmorty.data.model.CharacterResponse

class RickAndMortyApi(
    private val httpClient: HttpClient,
) {
    suspend fun getCharacters(page: Int): CharacterResponse {
        return httpClient.get("character"){
            parameter("page", page)
        }.body()
    }
}

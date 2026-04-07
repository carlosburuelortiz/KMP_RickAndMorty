package org.carlosburuel.tickandmorty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
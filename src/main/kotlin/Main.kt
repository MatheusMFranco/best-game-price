package org.magalzim

import org.magalzim.csv.GenerateFile
import org.magalzim.log.PrintInfo
import org.magalzim.model.GenericGame
import org.magalzim.service.GameService
import java.net.URLEncoder

val service = GameService()
const val MIN_PRICE = 15.0
const val MAX_GAMES = 3
const val GAME = "Sonic the Hedgehog"

fun main() {
    val gamesToPresent = mutableListOf<GenericGame>()
    val file = GenerateFile()
    val result = runCatching {
        service.findGames(URLEncoder.encode(GAME, "utf-8"))
            .take(MAX_GAMES)
            .map { game -> gamesToPresent.add(game) }
        gamesToPresent.forEach {
            val game = service.findById((it.gameID))
            file.list.add(game)
            PrintInfo.print(game)
        }
        file.writeCsvFile()
    }
    result.onFailure {
        println("Please, don't buy this game...")
    }
}
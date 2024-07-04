package org.magalzim

import org.magalzim.view.GameManager

const val MIN_PRICE = 15.0
const val MAX_GAMES = 3
const val GAME = "STEINS;GATE"

fun main() {
    val gameManager = GameManager()
    gameManager.prepareStores()
    gameManager.add(GAME, MIN_PRICE, MAX_GAMES)
    gameManager.print()
    gameManager.terminate()
}

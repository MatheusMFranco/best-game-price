package org.magalzim

import org.magalzim.database.Database
import org.magalzim.model.CollectorReview
import org.magalzim.model.Gamer
import org.magalzim.model.GeneralReview
import org.magalzim.model.TechnicalReview
import org.magalzim.repository.GameReviewRepository
import org.magalzim.repository.GamerRepository
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
    review()
    gamer()
}

fun gamer() {
    val gamer = Gamer("Monica", "monica@email.com")

    val manager = Database.getEntityManager()
    val gamerRepository = GamerRepository(manager)
    val reviewRepository = GameReviewRepository(manager)

    gamer.review = reviewRepository.findById(3)

    gamerRepository.add(gamer)

    val gamersList = gamerRepository.list()

    println(gamersList)

    manager.close()

}

fun review() {
    val general = GeneralReview("TOP")
    val unbelievable = TechnicalReview(
        "UNBELIEVABLE",
        "This is so good",
        10.0,
        10.0,
        10.0,
        10.0,
        10.0
    )
    val excellent = CollectorReview(
        "EXCELLENT",
        "Almost perfect!",
        9.0,
        9.0
    )
    val nice = CollectorReview(
        "NICE",
        "Something is bad",
        7.0,
        7.0
    )
    val bad = CollectorReview(
        "BAD",
        "No, please... garbage.",
        2.0,
        1.0
    )

    val manager = Database.getEntityManager()
    val reviewRepository = GameReviewRepository(manager)

    reviewRepository.add(general)
    reviewRepository.add(unbelievable)
    reviewRepository.add(excellent)
    reviewRepository.add(nice)
    reviewRepository.add(bad)

    val listReviews = reviewRepository.list()
    println(listReviews)

    manager.close()
}

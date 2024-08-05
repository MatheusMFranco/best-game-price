package org.magalzim.model

sealed class GameReview(val reviewTitle: String, var id:Int = 0) {
    open fun verifyRate(rate: Double): Double {
        return if (rate < 0 || rate > 10) {
            throw Exception("The rating should be between 0 and 10")
        } else {
            rate
        }
    }
}
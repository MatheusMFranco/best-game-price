package org.magalzim.model

class CollectorReview(
    reviewTitle: String,
    val collectorAnalysis: String,
    val achievementRating: Double,
    val boxRating: Double,
    id:Int = 0,
): GameReview(reviewTitle, id) {
    override fun verifyRate(rate: Double): Double {
        return if (rate < -1 || rate > 10) {
            throw Exception("The rating should be between -1 and 10")
        } else {
            rate
        }
    }

    override fun toString(): String {
        return "Collector Review\n" +
                "Type: $reviewTitle\n" +
                "Id: $id\n" +
                "Analysis: $collectorAnalysis\n" +
                "Achievement: $achievementRating\n" +
                "Box: $boxRating\n"

    }
}
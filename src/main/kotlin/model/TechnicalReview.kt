package org.magalzim.model

class TechnicalReview(
    reviewTitle: String,
    val technicalAnalysis: String,
    val performanceRating: Double,
    val visualRating: Double,
    val soundRating: Double,
    val gameplayRating: Double,
    val storyRating: Double,
    id:Int = 0,
): GameReview(reviewTitle, id) {
    override fun verifyRate(rate: Double): Double {
        return if (rate < 1 || rate > 10) {
            throw Exception("The rating should be between 1 and 10")
        } else {
            rate
        }
    }

    override fun toString(): String {
        return "Technical Review\n" +
                "Type: $reviewTitle\n" +
                "Id: $id\n" +
                "Analysis: $technicalAnalysis\n" +
                "Performance: $performanceRating\n" +
                "Visual: $visualRating\n" +
                "Sound: $soundRating\n" +
                "Gameplay: $gameplayRating\n" +
                "Story: $storyRating\n"

    }
}
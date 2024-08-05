package org.magalzim.model

class GeneralReview(
    reviewTitle: String,
    id:Int = 0,
): GameReview(reviewTitle, id) {
    override fun toString(): String {
        return "Collector Review\n" +
                "Type: $reviewTitle\n" +
                "Id: $id\n"

    }
}
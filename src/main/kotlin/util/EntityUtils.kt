package org.magalzim.util

import org.magalzim.database.*
import org.magalzim.model.*

fun DownloadableContent.toEntity(): DownloadableContentEntity {
    return DownloadableContentEntity(
        this.game.id,
        this.game.thumb,
        this.game.bestPriceEver,
        this.game.bestPriceEverDate,
        this.game.bestPriceNow,
        this.game.name,
        this.game.store.toEntity(),
        this.game.discount,
        this.baseGame
    )
}

fun Store.toEntity(): StoreEntity {
    return StoreEntity(this.storeID, this.storeName)
}

fun Game.toEntity(): GameEntity {
    return GameEntity(
        this.id,
        this.thumb,
        this.bestPriceEver,
        this.bestPriceEverDate,
        this.bestPriceNow,
        this.name,
        this.store.toEntity(),
        this.discount
    )
}

fun GameReview.toEntity(): GameReviewEntity {
    return when (this) {
        is TechnicalReview -> TechnicalReviewEntity(
            this.reviewTitle,
            this.technicalAnalysis,
            this.performanceRating,
            this.visualRating,
            this.soundRating,
            this.gameplayRating,
            this.storyRating,
            this.id
        )
        is CollectorReview -> CollectorReviewEntity(
            this.reviewTitle,
            this.collectorAnalysis,
            this.achievementRating,
            this.boxRating,
            this.id
        )
        else -> GeneralReviewEntity(this.reviewTitle, this.id)
    }
}

fun GameReviewEntity.toModel(): GameReview {
    return when (this) {
        is TechnicalReviewEntity -> TechnicalReview (
            this.reviewTitle,
            this.technicalAnalysis,
            this.performanceRating,
            this.visualRating,
            this.soundRating,
            this.gameplayRating,
            this.storyRating,
            this.id
        )
        is CollectorReviewEntity -> CollectorReview (
            this.reviewTitle,
            this.collectorAnalysis,
            this.achievementRating,
            this.boxRating,
            this.id
        )
        else -> GeneralReview(this.reviewTitle, this.id)
    }
}

fun Gamer.toEntity(): GamerEntity {
    return GamerEntity(this.id, this.name, this.email, this.review.toEntity())
}

fun GamerEntity.toModel(): Gamer {
    return Gamer(this.name, this.email, this.id)
}
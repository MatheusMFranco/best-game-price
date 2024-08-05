package org.magalzim.database

import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table

@Entity
@Table(name = "game_reviews")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ReviewType", discriminatorType = DiscriminatorType.STRING)
sealed class GameReviewEntity(
    val reviewTitle: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
)

@Entity
@DiscriminatorValue("General")
class GeneralReviewEntity(
    reviewTitle: String = "General Review",
    id: Int = 0
) : GameReviewEntity(reviewTitle, id)

@Entity
@DiscriminatorValue("Technical")
class TechnicalReviewEntity(
    reviewTitle: String = "Technical Review",
    val technicalAnalysis: String = "Perfect",
    val performanceRating: Double = 10.0,
    val visualRating: Double = 10.0,
    val soundRating: Double = 10.0,
    val gameplayRating: Double = 10.0,
    val storyRating: Double = 10.0,
    id: Int = 0
) : GameReviewEntity(reviewTitle, id)

@Entity
@DiscriminatorValue("Collector")
class CollectorReviewEntity(
    reviewTitle: String = "Collector Review",
    val collectorAnalysis: String = "Perfect",
    val achievementRating: Double = 10.0,
    val boxRating: Double = 10.0,
    id: Int = 0
) : GameReviewEntity(reviewTitle, id)

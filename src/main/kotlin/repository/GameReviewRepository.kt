package org.magalzim.repository

import org.magalzim.database.GameReviewEntity
import org.magalzim.model.GameReview
import org.magalzim.util.toEntity
import org.magalzim.util.toModel

import javax.persistence.EntityManager

class GameReviewRepository(manager: EntityManager): Repository<GameReview, GameReviewEntity>(manager, GameReviewEntity::class.java) {

    override fun toEntity(model: GameReview): GameReviewEntity {
        return model.toEntity()
    }

    override fun toModel(entity: GameReviewEntity): GameReview {
        return entity.toModel()
    }
}
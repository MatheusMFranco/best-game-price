package org.magalzim.repository

import org.magalzim.database.GameEntity
import org.magalzim.model.Game
import org.magalzim.util.toEntity
import org.magalzim.util.toModel

import javax.persistence.EntityManager

class GameRepository(manager: EntityManager): Repository<Game, GameEntity>(manager, GameEntity::class.java) {

    override fun toEntity(model: Game): GameEntity {
        return model.toEntity()
    }

    override fun toModel(entity: GameEntity): Game {
        return entity.toModel().apply { store = entity.store.toModel() }
    }
}
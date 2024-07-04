package org.magalzim.repository

import org.magalzim.database.GameEntity
import org.magalzim.database.StoreEntity
import org.magalzim.model.Game
import org.magalzim.model.Store
import org.magalzim.util.toEntity
import org.magalzim.util.toModel

import javax.persistence.EntityManager

class StoreRepository(manager: EntityManager): Repository<Store, StoreEntity>(manager, StoreEntity::class.java) {

    override fun toEntity(model: Store): StoreEntity {
        return model.toEntity()
    }

    override fun toModel(entity: StoreEntity): Store {
        return entity.toModel()
    }
}
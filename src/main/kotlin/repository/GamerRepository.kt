package org.magalzim.repository

import org.magalzim.database.GamerEntity
import org.magalzim.model.Gamer
import org.magalzim.util.toEntity
import org.magalzim.util.toModel
import javax.persistence.EntityManager

class GamerRepository(manager: EntityManager): Repository<Gamer, GamerEntity>(manager, GamerEntity::class.java) {

    override fun toEntity(model: Gamer): GamerEntity {
        return model.toEntity()
    }

    override fun toModel(entity: GamerEntity): Gamer {
        return entity.toModel().apply { review = entity.review.toModel() }
    }
}
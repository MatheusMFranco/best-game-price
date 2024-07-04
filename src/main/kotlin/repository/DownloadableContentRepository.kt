package org.magalzim.repository

import org.magalzim.database.DownloadableContentEntity
import org.magalzim.model.DownloadableContent
import org.magalzim.util.toEntity
import org.magalzim.util.toModel

import javax.persistence.EntityManager

class DownloadableContentRepository(manager: EntityManager): Repository<DownloadableContent, DownloadableContentEntity>(manager, DownloadableContentEntity::class.java) {

    override fun toEntity(model: DownloadableContent): DownloadableContentEntity {
        return model.toEntity()
    }

    override fun toModel(entity: DownloadableContentEntity): DownloadableContent {
        val newGame = entity.toModel().game
        newGame.store = entity.store.toModel()
        return entity.toModel().apply { game = newGame }
    }
}
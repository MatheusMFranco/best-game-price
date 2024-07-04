package org.magalzim.util

import org.magalzim.database.DownloadableContentEntity
import org.magalzim.database.GameEntity
import org.magalzim.database.StoreEntity
import org.magalzim.model.DownloadableContent
import org.magalzim.model.Game
import org.magalzim.model.Store

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
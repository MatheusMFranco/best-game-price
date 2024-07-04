package org.magalzim.util

import org.magalzim.database.DownloadableContentEntity
import org.magalzim.database.GameEntity
import org.magalzim.database.StoreEntity
import org.magalzim.model.DownloadableContent
import org.magalzim.model.Game
import org.magalzim.model.Store

fun DownloadableContentEntity.toModel(): DownloadableContent {
    return DownloadableContent(
        Game(this.name,
        this.currentPrice,
        this.bestPriceEver,
        this.thumb,
        this.bestPriceEverDate,
        this.id,
        this.discount),
        this.baseGame
    )
}

fun StoreEntity.toModel(): Store {
    return Store(this.id, this.name)
}

fun GameEntity.toModel(): Game {
    return Game(
        this.name,
        this.currentPrice,
        this.bestPriceEver,
        this.thumb,
        this.bestPriceEverDate,
        this.id,
        this.discount
    )
}
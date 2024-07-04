package org.magalzim.database

import java.util.*
import javax.persistence.*

@Entity
@DiscriminatorValue("DLC")
class DownloadableContentEntity(
    id: Int = 0,
    thumb: String = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/2923300/capsule_616x353.jpg",
    bestPriceEver: Double = 0.0,
    bestPriceEverDate: Date = Date(),
    currentPrice: Double = 0.0,
    name: String = "Banana",
    store: StoreEntity = StoreEntity(),
    discount: Discount = Discount(),
    val baseGame: String = ""
): GameEntity(
    id,
    thumb,
    bestPriceEver,
    bestPriceEverDate,
    currentPrice,
    name,
    store,
    discount,
)

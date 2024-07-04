package org.magalzim.database

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "games")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
open class GameEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Int = 0,
    open val thumb: String = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/2923300/capsule_616x353.jpg",
    open val bestPriceEver: Double = 0.0,
    open val bestPriceEverDate: Date = Date(),
    open val currentPrice: Double = 0.0,
    open val name: String = "Banana",
    @ManyToOne
    open val store: StoreEntity = StoreEntity(),
    @Embedded
    open val discount: Discount = Discount()
)

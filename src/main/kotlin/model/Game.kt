package org.magalzim.model

import org.magalzim.database.Discount
import org.magalzim.enums.StoreEnum
import org.magalzim.service.GameService
import java.util.Date

data class Game (
    val name: String,
    val bestPriceNow: Double,
    val bestPriceEver: Double
): Product() {
    var store: Store = Store(StoreEnum.EPIC.id, StoreEnum.EPIC.name)
    var discount: Discount = Discount()
    var id: Int = 0
    var thumb = ""
    var bestPriceEverDate = Date()
    var bestPriceStore: String = "Steam"
    var bestPriceStoreID: Int = 0
        set(value) {
            bestPriceStore = findStore(value)
        }

    constructor(name: String, bestPriceNow: Double, bestPriceEver: Double, bestPriceStore: String) :
            this(name, bestPriceNow, bestPriceEver) {
        this.bestPriceStore = bestPriceStore;
    }

    constructor(name: String, bestPriceNow: Double, bestPriceEver: Double, bestPriceStoreID: Int) :
            this(name, bestPriceNow, bestPriceEver) {
                this.bestPriceStore = findStore(bestPriceStoreID)
            }

    constructor(name: String, bestPriceNow: Double, bestPriceEver: Double, bestPriceStore: String, thumb: String, bestPriceEverDate: Date) :
            this(name, bestPriceNow, bestPriceEver) {
        this.bestPriceStore = bestPriceStore
        this.thumb = thumb
        this.bestPriceEverDate = bestPriceEverDate
    }

    constructor(name: String, bestPriceNow: Double, bestPriceEver: Double, store: Store, thumb: String, bestPriceEverDate: Date, id: Int) :
            this(name, bestPriceNow, bestPriceEver) {
        this.thumb = thumb
        this.bestPriceEverDate = bestPriceEverDate
        this.id = id
        this.store = store
    }

    constructor(name: String, bestPriceNow: Double, bestPriceEver: Double, store: Store, thumb: String, bestPriceEverDate: Date, id: Int, discount: Discount) :
            this(name, bestPriceNow, bestPriceEver) {
        this.thumb = thumb
        this.bestPriceEverDate = bestPriceEverDate
        this.id = id
        this.discount = discount
        this.store = store
    }

    constructor(name: String, bestPriceNow: Double, bestPriceEver: Double, thumb: String, bestPriceEverDate: Date, id: Int) :
            this(name, bestPriceNow, bestPriceEver) {
        this.thumb = thumb
        this.bestPriceEverDate = bestPriceEverDate
        this.id = id
    }

    constructor(name: String, bestPriceNow: Double, bestPriceEver: Double, thumb: String, bestPriceEverDate: Date, id: Int, discount: Discount) :
            this(name, bestPriceNow, bestPriceEver) {
        this.thumb = thumb
        this.bestPriceEverDate = bestPriceEverDate
        this.id = id
        this.discount = discount
    }

    init {
        if (bestPriceNow == 0.0) {
            println("IT'S FREEEEEEE!!!! \\o/")
        }
    }

    override fun toString(): String {
        val convertedBest = super.getMoney(bestPriceNow)
        val convertedEver = super.getMoney(bestPriceEver)
        return "$name - Now: [$convertedBest] ($bestPriceStore) | Best: [$convertedEver]"
    }

    companion object {
        fun findStore(storeId: Int): String {
            val service = GameService()
            val stores = service.findStores()
            val store = stores.first { it.storeID == storeId }
            return store.storeName
        }
        fun findBaseGame(name: String): String {
            val numberNameRegex = Regex("^(.*?\\d+)")
            val tokens = listOf("-", ":")
            var baseGame = ""
            var beforeToken = ""
            val tokenFound = tokens.any { token ->
                val index = name.indexOf(token)
                if (index != -1) {
                    beforeToken = name.substring(0, index)
                    val hasUpperNameBefore = beforeToken.split(" ").any { it == it.uppercase() }
                    val afterToken = name.substring(index + 1)
                    val hasUpperNameAfter = afterToken.split(" ").any { it == it.uppercase() }
                    val numberAfter = afterToken.split(" ").any { it.matches(Regex("\\d+")) }
                    hasUpperNameBefore || hasUpperNameAfter || numberAfter
                } else {
                    false
                }
            }

            if (tokenFound) {
                baseGame = beforeToken
            } else if(numberNameRegex.find(name) != null) {
                baseGame = numberNameRegex.find(name)?.value ?: ""
            }
            return baseGame
        }
    }
}
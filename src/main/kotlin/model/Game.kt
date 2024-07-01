package org.magalzim.model

import org.magalzim.service.GameService

data class Game (
    val name: String,
    val bestPriceNow: Double,
    private val bestPriceEver: Double,
): Product() {
    var bestPriceStore: String? = "Steam"
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
    }
}
package org.magalzim.log

import org.magalzim.MIN_PRICE
import org.magalzim.model.CheapShark
import org.magalzim.model.Game
import org.magalzim.util.transformToDate

class PrintInfo {
    companion object {

        fun print(gameInfo: CheapShark) {
            val title = gameInfo.info.title
            println("\n* $title\n")
            val deals = gameInfo.deals
            deals.sortBy { it.price }
            val bestPrice = deals.first()
            val worstPrice = deals.last()
            val stores = deals.filter { it.price <= MIN_PRICE }
            stores.forEach { println("Store: " + Game.findStore(it.storeID) + " | Price: $" + it.price) }
            val store = bestPrice.storeID
            val cheapest = gameInfo.cheapestPriceEver;
            val game = Game(
                bestPriceEver = cheapest.price,
                name = title,
                bestPriceNow = bestPrice.price,
            )
            if (bestPrice.price == 0.0) {
                game.let { it.bestPriceStore = "Epic" }
            } else {
                game.bestPriceStoreID = store
            }
            println("\n$game - " + cheapest.date.transformToDate())
            println(
                String.format(
                    "The worst price is $%.2f from %s",
                    worstPrice.price,
                    Game.findStore(worstPrice.storeID)
                )
            )
        }
    }
}
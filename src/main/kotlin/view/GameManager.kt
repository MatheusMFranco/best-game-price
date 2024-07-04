package org.magalzim.view

import org.magalzim.csv.GenerateFile
import org.magalzim.database.Database
import org.magalzim.database.Discount
import org.magalzim.enums.StoreEnum
import org.magalzim.log.PrintInfo
import org.magalzim.model.DownloadableContent
import org.magalzim.model.Game
import org.magalzim.model.GenericGame
import org.magalzim.model.Store
import org.magalzim.repository.DownloadableContentRepository
import org.magalzim.repository.GameRepository
import org.magalzim.repository.StoreRepository
import org.magalzim.service.GameService
import java.net.URLEncoder
import java.util.*

class GameManager() {
    private val service = GameService()
    private val manager = Database.getEntityManager()
    private val storeRepository = StoreRepository(manager)
    private val gameRepository = GameRepository(manager)
    private val dlcRepository = DownloadableContentRepository(manager)

    fun print() {
        println(gameRepository.list())
    }

    fun add(gameName: String, minPrice: Double, maxSize: Int) {
        val gamesToPresent = mutableListOf<GenericGame>()
        val file = GenerateFile()
        val result = runCatching {
            service.findGames(URLEncoder.encode(gameName, "utf-8"))
                .take(maxSize)
                .map { game -> gamesToPresent.add(game) }
            if (gamesToPresent.isEmpty()) {
                println("This game does not exist or the name is incorrect.")
                return
            }
            gamesToPresent.forEach {
                val game = service.findById((it.gameID))
                val deals = game.deals.filter { deal ->
                    StoreEnum.entries.any { item ->
                        item.id == deal.storeID
                    }
                }
                if (deals.isEmpty()) {
                    return
                }
                val deal = deals.first()
                val info = game.info
                val cheapest = game.cheapestPriceEver
                val store = storeRepository.findById(deal.storeID)
                val model = Game(
                    info.title,
                    deal.price,
                    cheapest.price,
                    store,
                    info.thumb,
                    Date(cheapest.date * 1000),
                    0,
                    Discount(deal.retailPrice, deal.price)
                )
                PrintInfo.print(game, minPrice)
                file.list.add(game)
                commit(model, store)
                println(game)
            }
            file.writeCsvFile()
        }
        result.onFailure {
           println("Please, don't buy this game...")
        }
    }

    fun prepareStores() {
        val result = runCatching {
            StoreEnum.entries.map { storeRepository.add(Store(it.id, it.name))}
        }
        result.onFailure {
            println("Stores already added")
        }
    }

    fun terminate() {
        manager.close()
    }

    private fun commit(model: Game, store: Store) {
        val baseName = Game.findBaseGame(model.name)
        if (baseName.isNotBlank()){
            val newGame = model.copy(name = "${model.name} (SEQUENCE)")
            newGame.store = store;
            dlcRepository.add(DownloadableContent(newGame, baseName))
        } else {
            gameRepository.add(model)
        }
    }

}
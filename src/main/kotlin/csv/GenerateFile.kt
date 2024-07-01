package org.magalzim.csv

import org.magalzim.model.CheapShark
import java.io.File

class GenerateFile() {
    private val filePath = "games.csv"
    val list = mutableListOf<CheapShark>()

    fun writeCsvFile() {
        val file = File(filePath)
        file.bufferedWriter().use { out ->
            out.write("Game Name,Price\n")
            list.forEach { game ->
                val name = game.info.title
                val price = game.cheapestPriceEver.price
                out.write("$name,${String.format("%.2f", price)}\n")
            }
        }
        println("CSV file '$filePath' has been created successfully.")
    }
}
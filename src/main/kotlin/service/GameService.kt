package org.magalzim.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.magalzim.model.CheapShark
import org.magalzim.model.GenericGame
import org.magalzim.model.Store
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class GameService: Service<CheapShark> {

    private val api = "https://www.cheapshark.com/api/1.0"

    override fun findById(id: Int): CheapShark {
        return Gson().fromJson(connect("/games?id=$id"), CheapShark::class.java)
    }

    fun findGames(title: String): List<GenericGame> {
        val listType = object : TypeToken<List<GenericGame>>() {}.type
        return Gson().fromJson(connect("/games?title=$title"), listType)
    }

    fun findStores(): Array<Store> {
        return Gson().fromJson(connect("/stores"), Array<Store>::class.java)
    }

    private fun connect(path: String): String? {
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(api + path))
            .build()
        val response = client.send(request, BodyHandlers.ofString())
        return response.body()
    }

}
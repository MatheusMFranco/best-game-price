package org.magalzim.model

import org.magalzim.util.transformToDecimal

sealed class Product() {
    open fun getMoney(price: Double): String {
        val converted = price.transformToDecimal() * 6
        return "R$ $converted"
    }
}
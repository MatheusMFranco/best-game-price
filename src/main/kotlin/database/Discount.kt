package org.magalzim.database

import javax.persistence.Embeddable

@Embeddable
data class Discount(
    val originalPrice: Double = 1.0,
    val newPrice: Double = 1.0
) {
    private val discount = originalPrice - newPrice
    val percent = (discount / originalPrice) * 100
}
package org.magalzim.model

data class CheapShark(val info: Info, val cheapestPriceEver: CheapestPriceEver, val deals: Array<Deal>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CheapShark

        if (info != other.info) return false
        if (cheapestPriceEver != other.cheapestPriceEver) return false
        if (!deals.contentEquals(other.deals)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = info.hashCode()
        result = 31 * result + cheapestPriceEver.hashCode()
        result = 31 * result + deals.contentHashCode()
        return result
    }
}

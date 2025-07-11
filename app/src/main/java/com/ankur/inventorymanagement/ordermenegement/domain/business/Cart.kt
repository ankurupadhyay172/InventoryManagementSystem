package com.ankur.inventorymanagement.ordermenegement.domain.business

data class Cart(
    private val counts: MutableMap<Int, Int> = mutableMapOf()
) {

    fun addToCart(productId: Int, qty: Int = 1): Int {
        val newQty = (counts[productId] ?: 0) + qty
        counts[productId] = newQty
        return newQty
    }


    fun remove(productId: Int, qty: Int = 1): Int {
        val newQty = (counts[productId] ?: 0) - qty
        if (newQty <= 0) counts.remove(productId) else counts[productId] = newQty
        return counts[productId] ?: 0
    }

    fun getQty(productId: Int): Int = counts[productId] ?: 0
    fun snapshot(): Map<Int, Int> = counts.toMap()

    fun emptyCart(){
        counts.clear()
    }

    fun isEmpty(): Boolean = counts.isEmpty()
}

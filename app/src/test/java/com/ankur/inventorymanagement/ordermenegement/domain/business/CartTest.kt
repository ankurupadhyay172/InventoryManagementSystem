package com.ankur.inventorymanagement.ordermenegement.domain.business

import org.junit.Assert.*
import org.junit.Test


class CartTest {

    @Test
    fun `addToCart should increase quantity`() {
        val cart = Cart()
        cart.addToCart(1, 2)

        assertEquals(2, cart.getQty(1))
    }

    @Test
    fun `remove should decrease quantity`() {
        val cart = Cart(mutableMapOf(1 to 5))
        cart.remove(1, 2)

        assertEquals(3, cart.getQty(1))
    }

    @Test
    fun `remove should remove product when quantity is zero`() {
        val cart = Cart(mutableMapOf(1 to 2))
        cart.remove(1, 2)

        assertEquals(0, cart.getQty(1))
        assertFalse(cart.snapshot().containsKey(1))
    }

    @Test
    fun `emptyCart should clear all items`() {
        val cart = Cart(mutableMapOf(1 to 2, 2 to 3))
        cart.emptyCart()

        assertTrue(cart.snapshot().isEmpty())
    }

    @Test
    fun `isEmpty should return true for empty cart`() {
        val cart = Cart()
        assertTrue(cart.isEmpty())
    }

    @Test
    fun `snapshot should return copy of internal map`() {
        val cart = Cart(mutableMapOf(1 to 2))
        val snapshot = cart.snapshot()

        assertEquals(2, snapshot[1])
    }
}

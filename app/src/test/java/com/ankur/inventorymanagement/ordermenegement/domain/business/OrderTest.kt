package com.ankur.inventorymanagement.ordermenegement.domain.business

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Warehouse
import org.junit.Assert.*


import org.junit.Test

class OrderTest {

    @Test
    fun `order should contain correct user and warehouse`() {
        val user = User(1, "Ankur", Cart())
        val warehouse = Warehouse()
        val order = Order(user, warehouse)

        assertEquals(user, order.user)
        assertEquals(warehouse, order.warehouse)
    }

    @Test
    fun `checkout should execute without error`() {
        val order = Order(User(), Warehouse())
        order.checkout() // Add assertions if logic exists
        assertNotNull(order)
    }
}

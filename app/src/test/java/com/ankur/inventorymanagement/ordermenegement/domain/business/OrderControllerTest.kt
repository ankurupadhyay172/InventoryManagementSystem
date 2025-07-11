package com.ankur.inventorymanagement.ordermenegement.domain.business

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Warehouse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class OrderControllerTest{
    private lateinit var orderController: OrderController
    private lateinit var testUser: User

    @Before
    fun setup() {
        orderController = OrderController()
        testUser = User(1, "Ankur",Cart(mutableMapOf(1 to 2)))
    }

    @Test
    fun `add order should store it under correct user`() {
        val order = Order(testUser, Warehouse())
        orderController.add(order)

        val userOrders = orderController.ordersOf(testUser.userId)

        assertEquals(1, userOrders.size)
        assertEquals(order, userOrders[0])
    }
}
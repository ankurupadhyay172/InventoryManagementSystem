package com.ankur.inventorymanagement.ordermenegement.domain.business

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderController @Inject constructor(){

    private val _orderList        = mutableListOf<Order>()
    private val _userIdVsOrders   = mutableMapOf<Int, MutableList<Order>>()

    /** call this when checkout is successful */
    fun add(order: Order) {
        _orderList += order

        val bucket = _userIdVsOrders.getOrPut(order.user.userId) { mutableListOf() }
        bucket    += order
    }

    fun ordersOf(userId: Int): List<Order> =
        _userIdVsOrders[userId].orEmpty()
}
package com.ankur.inventorymanagement.ordermenegement.presentation

import androidx.lifecycle.ViewModel
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order
import com.ankur.inventorymanagement.ordermenegement.domain.business.OrderController
import com.ankur.inventorymanagement.ordermenegement.domain.usecase.GetUserUseCase
import com.ankur.inventorymanagement.ordermenegement.domain.usecase.PrepareOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    val userUseCase: GetUserUseCase,
    val prepareOrderUseCase: PrepareOrderUseCase,
    val orderController: OrderController
) :ViewModel(){
    val user = userUseCase()
    private var _currentOrder: Order? = null
    val currentOrder: Order? get() = _currentOrder


    fun getOrders():List<Order>{
        return orderController.ordersOf(user.userId)
    }


    fun prepareOrder():Order {
        val order = prepareOrderUseCase(user)   // Order copies cart Snapshot
        _currentOrder = order
        order.printInvoice()
        return order
    }

    fun checkout(order: Order?){
        order?.checkout()
        order?.invoice?.generateInvoice(order)
        _currentOrder = order
        currentOrder?.let(orderController::add)
    }

}
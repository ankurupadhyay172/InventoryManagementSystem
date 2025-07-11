package com.ankur.inventorymanagement.presentation

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Warehouse
import com.ankur.inventorymanagement.ordermenegement.domain.business.Cart
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order
import com.ankur.inventorymanagement.ordermenegement.domain.business.OrderController
import com.ankur.inventorymanagement.ordermenegement.domain.business.User
import com.ankur.inventorymanagement.ordermenegement.domain.usecase.GetUserUseCase
import com.ankur.inventorymanagement.ordermenegement.domain.usecase.PrepareOrderUseCase
import com.ankur.inventorymanagement.ordermenegement.presentation.OrderViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever


class OrderViewModelTest{
    private lateinit var viewModel: OrderViewModel
    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var prepareOrderUseCase: PrepareOrderUseCase
    private lateinit var orderController: OrderController

    private val dummyUser = User(1, "Ankur", Cart())
    private val dummyOrder = Order(dummyUser, Warehouse())

    @Before
    fun setup() {
        getUserUseCase = mock()
        prepareOrderUseCase = mock()
        orderController = mock()

        whenever(getUserUseCase()).thenReturn(dummyUser)
        whenever(prepareOrderUseCase(dummyUser)).thenReturn(dummyOrder)

        viewModel = OrderViewModel(
            userUseCase = getUserUseCase,
            prepareOrderUseCase = prepareOrderUseCase,
            orderController = orderController
        )
    }

    @Test
    fun prepareOrder_shouldReturnOrder_andUpdateCurrentOrder() {
        val result = viewModel.prepareOrder()

        assertEquals(dummyOrder, result)
        assertEquals(dummyOrder, viewModel.currentOrder)

        verify(getUserUseCase).invoke()
        verify(prepareOrderUseCase).invoke(dummyUser)
    }

    @Test
    fun checkout_shouldAddOrderToController() {
        viewModel.checkout(dummyOrder)

        verify(orderController).add(dummyOrder)
    }


}
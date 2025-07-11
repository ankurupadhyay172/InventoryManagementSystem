package com.ankur.inventorymanagement.ordermenegement.presentation

import androidx.lifecycle.ViewModel
import com.ankur.inventorymanagement.inventorymanagement.domain.business.Address
import com.ankur.inventorymanagement.inventorymanagement.domain.business.ProductCategory
import com.ankur.inventorymanagement.inventorymanagement.domain.usecase.GetWarehouseUseCase
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order
import com.ankur.inventorymanagement.ordermenegement.domain.business.OrderController
import com.ankur.inventorymanagement.ordermenegement.domain.business.User
import com.ankur.inventorymanagement.ordermenegement.domain.usecase.AddToCartUseCase
import com.ankur.inventorymanagement.ordermenegement.domain.usecase.GetUserUseCase
import com.ankur.inventorymanagement.ordermenegement.domain.usecase.PrepareOrderUseCase
import com.ankur.inventorymanagement.ordermenegement.domain.usecase.RemoveFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
                                        val userUseCase: GetUserUseCase,
                                        val getWarehouseUseCase: GetWarehouseUseCase,
                                        val addToCartUseCase: AddToCartUseCase,
                                        val removeFromCartUseCase: RemoveFromCartUseCase,
                                       )
                                        : ViewModel() {
    val user = userUseCase()

    private val _quantities = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val quantities: StateFlow<Map<Int, Int>> = _quantities.asStateFlow()

    private val _uiEvents = MutableSharedFlow<String>()
    val uiEvents = _uiEvents.asSharedFlow()

    private val _categories = MutableStateFlow<List<ProductCategory>>(emptyList())
    val categories: StateFlow<List<ProductCategory>> = _categories




    private val cart = user.cart

    init {
        loadCategories()
    }

    fun loadCategories() {
        _categories.value = getWarehouseUseCase.invoke().inventory.productCategoryList
    }

    fun clearCart(){
        cart.emptyCart()
        emit()
    }
    fun add(productId: Int, qty: Int = 1, msg: (String) -> Unit) {
       addToCartUseCase(cart, productId, qty){
           msg(it)
       }
        emitQuantities()

    }


    fun remove(productId: Int, qty: Int = 1) {
        removeFromCartUseCase(cart, productId, qty)
        emitQuantities()
    }

    private fun emitQuantities() {
        _quantities.value = cart.snapshot().toMap()
    }
    private fun emit() {
        _quantities.value = cart.snapshot()
    }
}


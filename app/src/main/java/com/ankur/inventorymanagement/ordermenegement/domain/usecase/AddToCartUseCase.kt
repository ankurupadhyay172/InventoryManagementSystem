package com.ankur.inventorymanagement.ordermenegement.domain.usecase

import com.ankur.inventorymanagement.inventorymanagement.domain.usecase.GetWarehouseUseCase
import com.ankur.inventorymanagement.ordermenegement.domain.business.Cart
import javax.inject.Inject


class AddToCartUseCase @Inject constructor(
    private val getWarehouseUseCase: GetWarehouseUseCase
) {
    operator fun invoke(cart: Cart,productId: Int, qty: Int,message:(String)->Unit){

        val warehouse = getWarehouseUseCase()   // another use case
        val stockLeft = warehouse.getAvailableProductsCount(productId)

         if (cart.getQty(productId) + qty <= stockLeft) {
            cart.addToCart(productId, qty)

        } else {
             message("Only $stockLeft item available")
        }
    }
}


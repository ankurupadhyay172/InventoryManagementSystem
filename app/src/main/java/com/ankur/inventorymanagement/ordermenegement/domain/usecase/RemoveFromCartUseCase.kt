package com.ankur.inventorymanagement.ordermenegement.domain.usecase

import com.ankur.inventorymanagement.ordermenegement.domain.business.Cart
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor() {
    operator fun invoke(cart: Cart, productId: Int, qty: Int = 1) {
        cart.remove(productId, qty)
    }
}

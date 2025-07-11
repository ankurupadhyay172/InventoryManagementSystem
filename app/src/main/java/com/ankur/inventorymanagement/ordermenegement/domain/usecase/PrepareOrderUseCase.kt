package com.ankur.inventorymanagement.ordermenegement.domain.usecase

import com.ankur.inventorymanagement.inventorymanagement.domain.usecase.GetWarehouseUseCase
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order
import com.ankur.inventorymanagement.ordermenegement.domain.business.User
import javax.inject.Inject

class PrepareOrderUseCase @Inject constructor(
    private val getWarehouseUseCase: GetWarehouseUseCase
) {
    operator fun invoke(user:User): Order {

        val warehouse = getWarehouseUseCase()
        return Order(user, warehouse)
    }
}

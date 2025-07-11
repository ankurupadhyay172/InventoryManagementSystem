package com.ankur.inventorymanagement.inventorymanagement.domain.usecase

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Warehouse
import com.ankur.inventorymanagement.inventorymanagement.domain.business.WarehouseController
import javax.inject.Inject


class GetWarehouseUseCase @Inject constructor(
    private val warehouseController: WarehouseController
) {
    operator fun invoke(): Warehouse {
        return warehouseController.getWarehouse()
    }
}


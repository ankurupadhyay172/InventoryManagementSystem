package com.ankur.inventorymanagement.inventorymanagement.domain.business

import com.ankur.inventorymanagement.inventorymanagement.domain.repository.InventoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WarehouseController @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    private val warehouseList: MutableList<Warehouse> = mutableListOf(Warehouse())

    private var selectedWarehouse: Warehouse? = null

    fun getWarehouse(): Warehouse {
        // Only select and decorate once
        if (selectedWarehouse == null) {
            val selected = NearestWarehouseSelectionStrategy().selectWarehouse(warehouseList)
            selectedWarehouse = inventoryRepository.addWarehouseProducts(selected)
        }
        return selectedWarehouse!!
    }

    fun addWarehouse(warehouse: Warehouse) {
        warehouseList.add(warehouse)
    }

    fun removeWarehouse(warehouse: Warehouse) {
        warehouseList.remove(warehouse)
    }
}



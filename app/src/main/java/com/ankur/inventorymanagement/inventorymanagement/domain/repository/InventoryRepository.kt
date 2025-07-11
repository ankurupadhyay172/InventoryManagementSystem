package com.ankur.inventorymanagement.inventorymanagement.domain.repository

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Warehouse

interface InventoryRepository {
    fun addWarehouseProducts(warehouse: Warehouse):Warehouse
}
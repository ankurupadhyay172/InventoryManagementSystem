package com.ankur.inventorymanagement.inventorymanagement.domain.business

abstract class WarehouseSelectionStrategy {
    abstract fun selectWarehouse(warehouseList: List<Warehouse>): Warehouse
}

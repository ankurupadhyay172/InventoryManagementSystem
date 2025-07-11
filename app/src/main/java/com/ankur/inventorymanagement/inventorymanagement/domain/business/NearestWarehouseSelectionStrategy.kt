package com.ankur.inventorymanagement.inventorymanagement.domain.business

class NearestWarehouseSelectionStrategy : WarehouseSelectionStrategy() {
    override fun selectWarehouse(warehouseList: List<Warehouse>): Warehouse {
        return warehouseList.first() // equivalent to warehouseList.get(0)
    }
}

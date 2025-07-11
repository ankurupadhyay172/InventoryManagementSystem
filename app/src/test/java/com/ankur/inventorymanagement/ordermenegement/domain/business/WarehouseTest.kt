package com.ankur.inventorymanagement.ordermenegement.domain.business

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Address
import com.ankur.inventorymanagement.inventorymanagement.domain.business.Inventory
import com.ankur.inventorymanagement.inventorymanagement.domain.business.Warehouse
import org.junit.Assert.*
import org.junit.Test

class WarehouseTest {

    @Test
    fun `new warehouse should initialize with empty inventory`() {
        val warehouse = Warehouse()
        assertNotNull(warehouse.inventory)
        assertTrue(warehouse.inventory.productCategoryList.isEmpty())
    }

    @Test
    fun `inventory should persist categories`() {
        val inventory = Inventory()
        inventory.addCategory(1, "Dairy", 40.0)

        val warehouse = Warehouse(Address(), inventory)
        assertEquals(1, warehouse.inventory.productCategoryList.size)
    }
}

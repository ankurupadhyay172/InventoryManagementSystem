package com.ankur.inventorymanagement.ordermenegement.domain.business

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Inventory
import com.ankur.inventorymanagement.inventorymanagement.domain.business.Product
import org.junit.Assert.*
import org.junit.Test

class InventoryTest {

    @Test
    fun `addCategory should add new category`() {
        val inventory = Inventory()
        inventory.addCategory(1, "Pepsi 500ml", 50.0)

        assertEquals(1, inventory.productCategoryList.size)
        assertEquals("Pepsi 500ml", inventory.productCategoryList[0].productCategoryName)
    }

    @Test
    fun `addCategoryProduct should add product to existing category`() {
        val inventory = Inventory()
        inventory.addCategory(1, "Pepsi", 50.0)
        inventory.addCategoryProduct(Product(1, "Pepsi 500ml"), 1)

        val category = inventory.productCategoryList.first()
        assertEquals(1, category.products.size)
        assertEquals("Pepsi 500ml", category.products[0].productName)
    }
}

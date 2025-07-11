package com.ankur.inventorymanagement.ordermenegement.domain.business

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Product
import org.junit.Assert.*
import org.junit.Test

class ProductTest {

    @Test
    fun `product should have correct id and name`() {
        val product = Product(1, "Pepsi 500ml")

        assertEquals(1, product.productId)
        assertEquals("Pepsi 500ml", product.productName)
    }
}

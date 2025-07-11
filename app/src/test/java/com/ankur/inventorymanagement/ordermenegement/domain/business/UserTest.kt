package com.ankur.inventorymanagement.ordermenegement.domain.business

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Address
import org.junit.Assert.*
import org.junit.Test

class UserTest {

    @Test
    fun `user should store and return cart`() {
        val cart = Cart(mutableMapOf(1 to 2))
        val user = User(1, "Ankur", cart)

        assertEquals(cart, user.getUserCart())
    }

    @Test
    fun `user should hold correct address`() {
        val user = User()
        user.address = Address(1234, "State", "City")

        assertEquals("City", user.address.city)
        assertEquals("State", user.address.state)
        assertEquals("123456", user.address.pinCode)
    }
}

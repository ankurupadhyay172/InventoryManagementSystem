package com.ankur.inventorymanagement.ordermenegement.domain.business

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Address

data class User(var userId:Int=0,var userName:String="",var cart: Cart){
    constructor():this(cart = Cart())
    var address = Address()
    fun getUserCart(): Cart = cart
}


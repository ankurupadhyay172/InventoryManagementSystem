package com.ankur.inventorymanagement.ordermenegement.domain.business

enum class OrderStatus(var label:String) {
    PROCESS("In Progress"),
    OUT_FOR_DELIVERY("Out For Delivery"),
    DELIVERED("Delivered"),

}
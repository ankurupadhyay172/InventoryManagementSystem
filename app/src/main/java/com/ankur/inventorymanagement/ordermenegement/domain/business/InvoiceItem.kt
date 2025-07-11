package com.ankur.inventorymanagement.ordermenegement.domain.business

data class InvoiceItem(
    val categoryName: String,
    val unitPrice: Double,
    val quantity: Int,
    val subtotal: Double
)
package com.ankur.inventorymanagement.ordermenegement.domain.business

data class Invoice(
    var totalAmount: Double = 0.0,
    var tax: Int = 0,
    var finalAmount: Double = 0.0,
    val items: MutableList<InvoiceItem> = mutableListOf()
) {
    fun printInvoice(order: Order) {
        var totalPrice = 0.0

        for ((key, value) in order.productCategoryIdVsCountMap) {
            val productCategory = order.warehouse.inventory.getProductCategoryFromId(key)
            if (productCategory != null) {
                val price = productCategory.price * value
                totalPrice += price
                println("Category: ${productCategory.productCategoryName}, Quantity: $value, Subtotal: $price")
            } else {
                println("Product category not found for ID: $key")
            }
        }

        totalAmount = totalPrice
        tax = (totalAmount*(0.1)).toInt()
        finalAmount = totalAmount + tax

        println("Total Amount: $totalAmount")
        println("Tax: $tax")
        println("Final Amount: $finalAmount")
    }

    fun generateInvoice(order: Order) {
        items.clear() // Make sure to clear previous entries
        var totalPrice = 0.0

        for ((productCategoryId, quantity) in order.productCategoryIdVsCountMap) {
            val category = order.warehouse.inventory.getProductCategoryFromId(productCategoryId)
            if (category != null) {
                val subtotal = category.price * quantity
                totalPrice += subtotal

                // Add item to the invoice list
                items.add(
                    InvoiceItem(
                        categoryName = category.productCategoryName,
                        unitPrice = category.price,
                        quantity = quantity,
                        subtotal = subtotal
                    )
                )
            }
        }

        totalAmount = totalPrice
        tax = (totalAmount * 0.1).toInt()
        finalAmount = totalAmount + tax
    }
}

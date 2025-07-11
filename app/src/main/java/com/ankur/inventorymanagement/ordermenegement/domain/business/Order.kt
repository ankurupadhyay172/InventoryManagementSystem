package com.ankur.inventorymanagement.ordermenegement.domain.business


import com.ankur.inventorymanagement.inventorymanagement.domain.business.Address
import com.ankur.inventorymanagement.inventorymanagement.domain.business.Warehouse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicInteger

data class Order(var user: User, var warehouse: Warehouse){
    var productCategoryIdVsCountMap:MutableMap<Int,Int> = user.getUserCart().snapshot().toMutableMap()
    var address: Address = user.address
    var invoice: Invoice = Invoice()
    var payment = Payment(UpiPaymentMode()) //Default
    private val nanoCounter = AtomicInteger(0)
    var orderId = generateOrderId(user.userId)
    var status: OrderStatus = OrderStatus.OUT_FOR_DELIVERY
    val orderDate : LocalDateTime = LocalDateTime.now()
    val subtotal: Double by lazy {
        items.sumOf { it.price * it.qty }
    }

    var discountAmount: Double = 0.0

    val taxRate = 0.10  // 10%

    val taxAmount: Double by lazy {
        subtotal*taxRate
    }


    val total: Double by lazy {
        val afterDiscount = (subtotal - discountAmount).coerceAtLeast(0.0)
        val taxAmount = afterDiscount * taxRate
        (afterDiscount + taxAmount)
    }
    val items: List<OrderItem> by lazy {
        productCategoryIdVsCountMap.map { (categoryId, qty) ->
            val productCategory = warehouse.getCategoryById(categoryId)
            OrderItem(
                title = productCategory.productCategoryName,
                qty = qty,
                price = productCategory.price
            )
        }
    }
    fun checkout(){
        println(productCategoryIdVsCountMap)
        warehouse.removeItemsFromInventory(productCategoryIdVsCountMap)
        val isPaymentSuccess = payment.makePayment()
        if(isPaymentSuccess){
            user.getUserCart().emptyCart()
        }else{
            warehouse.addItemsToInventory(productCategoryIdVsCountMap)
        }
    }
    fun printInvoice(){
        invoice.printInvoice(this)
    }

    fun setPaymentMode(mode: PaymentMode){
        payment = Payment(mode)
    }


    /**  e.g.  U1‑20250706‑221045‑003  */
    fun generateOrderId(userId: Int): String {
        val ts = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyyMMdd‑HHmmss"))
        val suffix = nanoCounter.getAndIncrement().toString().padStart(3, '0')
        return "U$userId‑$ts‑$suffix"
    }

}
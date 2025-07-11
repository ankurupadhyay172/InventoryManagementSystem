package com.ankur.inventorymanagement.ordermenegement.domain.business

class Payment(val paymentMode: PaymentMode) {
    fun makePayment():Boolean{
        return paymentMode.makePayment()
    }
}

class UpiPaymentMode : PaymentMode {
    override fun makePayment(): Boolean {
        return true
    }
}

object CashOnDeliveryMode : PaymentMode {
    override fun makePayment() = true        // collect later
}

class CardPaymentMode : PaymentMode {
    override fun makePayment() = true        // stub
}

class NetBankingMode : PaymentMode {
    override fun makePayment() = true        // stub
}

interface PaymentMode{
    fun makePayment():Boolean
}
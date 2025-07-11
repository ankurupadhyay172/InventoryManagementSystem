package com.ankur.inventorymanagement.ordermenegement.domain.usecase

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Address
import com.ankur.inventorymanagement.ordermenegement.domain.business.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserCartUseCase @Inject constructor() {
    private val user = User().apply {
        userName = "Ankur"
        userId = 1
        address = Address(314001, "Dungarpur", "Rajasthan")
    }

    operator fun invoke(): User = user // ✅ return the same user every time
}

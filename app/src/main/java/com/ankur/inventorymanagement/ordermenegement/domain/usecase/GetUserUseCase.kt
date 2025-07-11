package com.ankur.inventorymanagement.ordermenegement.domain.usecase

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Address
import com.ankur.inventorymanagement.ordermenegement.domain.business.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCase @Inject constructor() {
    private val user = User().apply {
        userId = 1
        userName = "Ankur"
        address = Address(411014, "Pune", "Maharashtra")
    }

    operator fun invoke(): User = user
}


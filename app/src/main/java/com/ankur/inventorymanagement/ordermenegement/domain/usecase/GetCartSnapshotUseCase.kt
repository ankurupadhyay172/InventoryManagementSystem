package com.ankur.inventorymanagement.ordermenegement.domain.usecase

import javax.inject.Inject

class GetCartSnapshotUseCase @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) {
    operator fun invoke(): Map<Int, Int> {
        return getUserUseCase().getUserCart().snapshot().toMap()
    }
}


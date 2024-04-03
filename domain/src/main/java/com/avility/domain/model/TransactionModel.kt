package com.avility.domain.model

import java.time.LocalDateTime

data class TransactionModel(
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String,
    val recipeId: String?,
    val rrn: String?,
    val statusCode: String,
    val statusDescription: String,
    val createdAt: LocalDateTime,
    val indStatus: Status
)

enum class Status(val value: Int, val color: Long) {
    APPROVED(1, 0xFF4CAF50),
    REJECTED(-1, 0xFFDA1D3F),
    ANNULLED(0, 0xFF673AB7),
    UNDEFINED(99, 0xFFFFFF)
}

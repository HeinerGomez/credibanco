package com.avility.data.database.mappers

import com.avility.data.database.entities.TransactionEntity
import com.avility.domain.model.Status
import com.avility.domain.model.TransactionModel
import java.util.UUID

fun TransactionEntity.toModel() = TransactionModel(
    id = id,
    commerceCode,
    terminalCode,
    amount,
    card,
    recipeId,
    rrn,
    statusCode,
    statusDescription,
    createdAt,
    when(indStatus) {
        -1 -> Status.REJECTED
        0 -> Status.ANNULLED
        1 -> Status.APPROVED
        else -> Status.UNDEFINED
    }
)

fun TransactionModel.toEntity() = TransactionEntity(
    id = id,
    commerceCode = commerceCode,
    terminalCode = terminalCode,
    amount = amount,
    card = card,
    recipeId = recipeId,
    rrn = rrn,
    statusCode = statusCode,
    statusDescription = statusDescription,
    createdAt = createdAt,
    indStatus = indStatus.value
)
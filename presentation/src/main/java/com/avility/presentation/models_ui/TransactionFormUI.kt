package com.avility.presentation.models_ui

import com.avility.domain.model.Status
import com.avility.domain.model.TransactionModel
import java.time.LocalDateTime
import java.util.UUID

data class TransactionFormUI(
    val id: String = UUID.randomUUID().toString(),
    val commerceCode: String = "",
    val terminalCode: String = "",
    val amount: String = "",
    val card: String = "",
    val recipeId: String? = null,
    val rrn: String? = null,
    val statusCode: String = "",
    val statusDescription: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val indStatus: Status = Status.UNDEFINED
)

fun TransactionFormUI.toModel() = TransactionModel(
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
    indStatus = indStatus
)

fun TransactionModel.toUI() = TransactionFormUI(
    id, commerceCode, terminalCode, amount, card, recipeId, rrn, statusCode, statusDescription, createdAt, indStatus
)
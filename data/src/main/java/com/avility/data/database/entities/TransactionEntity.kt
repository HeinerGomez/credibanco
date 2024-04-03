package com.avility.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "commerce_code")
    val commerceCode: String,
    @ColumnInfo(name = "terminal_code")
    val terminalCode: String,
    val amount: String,
    val card: String,
    @ColumnInfo(name = "recipe_id")
    val recipeId: String? = null,
    val rrn: String? = null,
    @ColumnInfo(name = "status_code")
    val statusCode: String,
    @ColumnInfo(name = "status_description")
    val statusDescription: String,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime,
    @ColumnInfo(name = "ind_status")
    val indStatus: Int
)

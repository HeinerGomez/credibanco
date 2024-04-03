package com.avility.data.database.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.avility.data.database.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionLocalDataSource {

    @Query("SELECT * FROM transactionentity WHERE ind_status IN (:status) AND recipe_id LIKE :chunkRecipeId || '%'")
    fun getTransactionsByStatus(chunkRecipeId: String, status: List<Int>): Flow<List<TransactionEntity>>

    @Insert
    suspend fun insert(transaction: TransactionEntity)

    @Update
    suspend fun update(transaction: TransactionEntity)
}
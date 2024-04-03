package com.avility.domain.repository

import com.avility.domain.model.TransactionModel
import com.avility.domain.model.TransactionResultModel
import com.avility.domain.request.AnnulRequest
import com.avility.domain.request.AuthorizationRequest
import com.avility.shared.core.Resource
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun authorizeTransaction(bodyRequest: AuthorizationRequest): Resource<TransactionResultModel>

    suspend fun annulledTransaction(transaction: TransactionModel, bodyRequest: AnnulRequest): Resource<TransactionResultModel>

    suspend fun getTransactionsByStatus(chunkRecipeId: String, statusList: List<Int>): Flow<List<TransactionModel>>

}
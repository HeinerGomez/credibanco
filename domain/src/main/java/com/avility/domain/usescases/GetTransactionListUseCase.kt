package com.avility.domain.usescases

import com.avility.domain.model.TransactionModel
import com.avility.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionListUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(query: String = ""): Flow<List<TransactionModel>> {
        return transactionRepository.getTransactionsByStatus(query, listOf(1,0))
    }
}
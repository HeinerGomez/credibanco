package com.avility.domain.usescases

import com.avility.domain.model.TransactionModel
import com.avility.domain.model.TransactionResultModel
import com.avility.domain.repository.TransactionRepository
import com.avility.domain.request.AnnulRequest
import com.avility.shared.core.Resource
import javax.inject.Inject

class AnnulTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(data: TransactionModel): Resource<TransactionResultModel> {
        val body = AnnulRequest(
            receiptId = data.recipeId.orEmpty(),
            rrn = data.rrn.orEmpty()
        )

        return transactionRepository.annulledTransaction(data, body)
    }
}
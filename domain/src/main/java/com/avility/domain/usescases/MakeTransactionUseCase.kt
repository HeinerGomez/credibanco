package com.avility.domain.usescases

import com.avility.domain.model.TransactionModel
import com.avility.domain.model.TransactionResultModel
import com.avility.domain.repository.TransactionRepository
import com.avility.domain.request.AuthorizationRequest
import com.avility.shared.core.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MakeTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(data: TransactionModel): Resource<TransactionResultModel> {
        val body = AuthorizationRequest(
            id = data.id,
            commerceCode = data.commerceCode,
            terminalCode = data.terminalCode,
            amount = data.amount,
            card = data.card
        )
        return transactionRepository.authorizeTransaction(body)
    }
}
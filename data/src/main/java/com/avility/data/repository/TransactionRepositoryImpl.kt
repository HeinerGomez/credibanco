package com.avility.data.repository

import com.avility.shared.R
import com.avility.data.database.datasource.TransactionLocalDataSource
import com.avility.data.database.entities.TransactionEntity
import com.avility.data.database.mappers.toEntity
import com.avility.data.database.mappers.toModel
import com.avility.data.remote.datasource.CrediBancoAPIDataSource
import com.avility.data.utils.NetworkUtils
import com.avility.domain.model.TransactionModel
import com.avility.domain.model.TransactionResultModel
import com.avility.domain.repository.TransactionRepository
import com.avility.domain.request.AnnulRequest
import com.avility.domain.request.AuthorizationRequest
import com.avility.shared.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: CrediBancoAPIDataSource,
    private val localDataSource: TransactionLocalDataSource
) : TransactionRepository {

    override suspend fun authorizeTransaction(bodyRequest: AuthorizationRequest): Resource<TransactionResultModel> {
        var response: Resource<TransactionResultModel> = Resource.Error(
            message = R.string.unknown_error,
            data = TransactionResultModel()
        )

        runCatching {
            remoteDataSource.authorization(
                header = NetworkUtils.getHeaderTransaction(bodyRequest.commerceCode, bodyRequest.terminalCode),
                bodyRequest
            )
        }.onFailure {
            Timber.e(it)
        }.onSuccess {
            val indStatus = if (it.statusCode == "00") {
                1
            } else {
                -1
            }

            localDataSource.insert(TransactionEntity(
                id = UUID.randomUUID().toString(),
                commerceCode = bodyRequest.commerceCode,
                terminalCode = bodyRequest.terminalCode,
                amount = bodyRequest.amount,
                card = bodyRequest.card,
                createdAt = LocalDateTime.now(),
                indStatus = indStatus,
                recipeId = it.receiptId,
                rrn = it.rrn,
                statusCode = it.statusCode.orEmpty(),
                statusDescription = it.statusDescription.orEmpty()
            ))

            response = Resource.Success(
                data = TransactionResultModel(
                    resourceMessage = if (indStatus == 1) {
                        R.string.transaction_approved
                    } else {
                        R.string.transaction_rejected
                    }
                )
            )
        }

        return response
    }


    override suspend fun annulledTransaction(transaction: TransactionModel, bodyRequest: AnnulRequest): Resource<TransactionResultModel> {
        var response: Resource<TransactionResultModel> =  Resource.Error(
            message = R.string.unknown_error,
            data = TransactionResultModel()
        )

        runCatching {
            remoteDataSource.annulment(
                header = NetworkUtils.getHeaderTransaction(transaction.commerceCode, transaction.terminalCode),
                bodyRequest
            )
        }.onFailure {
            Timber.e(it)
        }.onSuccess {
            val indStatus = if (it.statusCode == "00") {
                0
            } else {
                1
            }

            val entity = transaction.toEntity().copy(
                indStatus = indStatus,
            )

            localDataSource.update(entity)

            response = Resource.Success(
                data = TransactionResultModel(
                    resourceMessage = if (indStatus == 0) {
                        R.string.transaction_annulled
                    } else {
                        R.string.unknown_error
                    }
                )
            )
        }

        return response
    }

    override suspend fun getTransactionsByStatus(chunkRecipeId: String, statusList: List<Int>): Flow<List<TransactionModel>> {
        return localDataSource.getTransactionsByStatus(chunkRecipeId, statusList).map { it.map { transactionEntity ->  transactionEntity.toModel() } }
    }
}
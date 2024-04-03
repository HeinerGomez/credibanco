package com.avility.data.remote.datasource

import com.avility.data.remote.dto.TransactionResponseDto
import com.avility.domain.request.AnnulRequest
import com.avility.domain.request.AuthorizationRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CrediBancoAPIDataSource {

    @POST("payments/authorization")
    suspend fun authorization(
        @Header("Authorization") header: String,
        @Body bodyRequest: AuthorizationRequest
    ): TransactionResponseDto

    @POST("payments/annulment")
    suspend fun annulment(
        @Header("Authorization") header: String,
        @Body bodyRequest: AnnulRequest
    ): TransactionResponseDto
}
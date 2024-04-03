package com.avility.data.utils

import java.util.Base64

object NetworkUtils {

    fun getHeaderTransaction(
        commerceCode: String,
        terminalCode: String
    ): String {
        val seedAuth = commerceCode + terminalCode
        return "Basic " + Base64.getEncoder().encodeToString(seedAuth.toByteArray())
    }
}
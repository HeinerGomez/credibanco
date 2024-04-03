package com.avility.presentation.screens.home

import androidx.annotation.StringRes
import com.avility.domain.model.TransactionModel

data class HomeState(
    val isLoading: Boolean = false,
    val query: String = "",
    val data: List<TransactionModel> = emptyList(),
    val mustShowDialogCreate: Boolean = false,
    val selectedTransaction: TransactionModel? = null,
    @StringRes
    val errorResource: Int? = null
)

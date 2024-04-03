package com.avility.presentation.screens.home

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.avility.domain.usescases.AnnulTransactionUseCase
import com.avility.domain.usescases.GetTransactionListUseCase
import com.avility.domain.usescases.MakeTransactionUseCase
import com.avility.presentation.BaseViewModel
import com.avility.presentation.R
import com.avility.presentation.models_ui.TransactionFormUI
import com.avility.presentation.models_ui.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val makeAuthorizationUseCase: MakeTransactionUseCase,
    private val getTransactionListUseCase: GetTransactionListUseCase,
    private val annulTransactionUseCase: AnnulTransactionUseCase
) : BaseViewModel<HomeState, HomeAction>(HomeState()) {

    init {
        loadData()
    }

    override fun dispatchAction(action: HomeAction) {
        when (action) {
            HomeAction.ResetError -> setState(uiState.value.copy(
                errorResource = null,
                query = ""
            ))
            is HomeAction.QuerySearchUpdated -> setState(uiState.value.copy(
                query = action.query
            ))
            HomeAction.Search -> loadData()
            HomeAction.OpenDialogCreateTransaction -> setState(uiState.value.copy(
                mustShowDialogCreate = true,
                selectedTransaction = null
            ))
            HomeAction.CloseDialogCreateTransaction -> setState(uiState.value.copy(
                mustShowDialogCreate = false,
                selectedTransaction = null
            ))
            is HomeAction.OpenDialogUpdateTransaction -> setState(uiState.value.copy(
                mustShowDialogCreate = false,
                selectedTransaction = action.selectedTransaction
            ))
            HomeAction.CloseDialogUpdateTransaction -> setState(uiState.value.copy(
                mustShowDialogCreate = false,
                selectedTransaction = null
            ))
            is HomeAction.CreateTransaction -> createTransaction(action)
            is HomeAction.AnnulTransaction -> annulTransaction(action)
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            setState(uiState.value.copy(
                isLoading = true
            ))
            delay(2_000L)
            getTransactionListUseCase(uiState.value.query).onEach {
                setState(uiState.value.copy(
                    isLoading = false,
                    data = it
                ))
            }.launchIn(this)
        }
    }

    private fun createTransaction(action: HomeAction.CreateTransaction) {
        viewModelScope.launch {
            if (isValidForm(action.transactionFormUI)) {
                setState(uiState.value.copy(
                    isLoading = true,
                    errorResource = null,
                    mustShowDialogCreate = false
                ))
                delay(2_000L)

                val result = makeAuthorizationUseCase(action.transactionFormUI.toModel())
                setState(uiState.value.copy(
                    isLoading = false,
                    errorResource = result.data?.resourceMessage ?: result.message
                ))
            } else {
                setState(uiState.value.copy(
                    isLoading = false,
                    errorResource = R.string.invalid_form_create_transaction
                ))
            }
        }
    }

    private fun annulTransaction(action: HomeAction.AnnulTransaction) {
        viewModelScope.launch {
            setState(uiState.value.copy(
                isLoading = true,
                errorResource = null,
                mustShowDialogCreate = false,
                selectedTransaction = null
            ))
            delay(2_000L)

            val result = annulTransactionUseCase(action.transactionFormUI.toModel())
            setState(uiState.value.copy(
                isLoading = false,
                errorResource = result.data?.resourceMessage ?: result.message
            ))
        }
    }

    private fun isValidForm(data: TransactionFormUI): Boolean {
        var isFormValid = true

        if (data.commerceCode.isBlank() || data.terminalCode.isBlank()
            || data.card.isBlank() || data.amount.isBlank() || !data.amount.isDigitsOnly()
            || !data.card.isDigitsOnly()) {
            isFormValid = false
        }

        return isFormValid
    }
}
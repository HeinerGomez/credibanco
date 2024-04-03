package com.avility.presentation.screens.home

import com.avility.domain.model.TransactionModel
import com.avility.presentation.models_ui.TransactionFormUI

sealed class HomeAction {
    object ResetError : HomeAction()
    class QuerySearchUpdated(val query: String) : HomeAction()
    object Search : HomeAction()
    class CreateTransaction(val transactionFormUI: TransactionFormUI) : HomeAction()
    class AnnulTransaction(val transactionFormUI: TransactionFormUI) : HomeAction()
    object OpenDialogCreateTransaction : HomeAction()
    object CloseDialogCreateTransaction: HomeAction()
    class OpenDialogUpdateTransaction(val selectedTransaction: TransactionModel) : HomeAction()
    object CloseDialogUpdateTransaction : HomeAction()
}
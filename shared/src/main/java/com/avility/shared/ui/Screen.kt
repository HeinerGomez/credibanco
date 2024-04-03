package com.avility.shared.ui

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object TransactionDetailScreen : Screen("transaction_detail_screen")
}
package com.avility.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avility.domain.model.Status
import com.avility.presentation.R
import com.avility.presentation.components.TransactionItem
import com.avility.presentation.models_ui.toUI
import com.avility.presentation.screens.home.dialogs.TransactionDialog
import com.avility.shared.ui.components.containers.BasicContainer
import com.avility.shared.ui.components.containers.MainContainer
import com.avility.shared.ui.components.elements_form.SearchTextField
import com.avility.shared.ui.components.others.LottieInfoScreen
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.styles.elements_form.TextFieldStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.value
    val applicationContext = LocalContext.current

    MainContainer(
        isLoading = state.isLoading,
        header = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_home),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
        headerPadding = false,
        headerColor = MaterialTheme.colorScheme.secondary
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                      viewModel.dispatchAction(HomeAction.OpenDialogCreateTransaction)
                    },
                    containerColor = MaterialTheme.colorScheme.tertiary
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ) { paddingValues ->
            if (!state.isLoading && state.data.isEmpty()) {
                BasicContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(paddingValues),
                    containerColor = Color.Transparent,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LottieInfoScreen(
                        resource = com.avility.shared.R.raw.empty_screen,
                        message = stringResource(R.string.empty_list)
                    )
                }
            }

            Column(Modifier.fillMaxSize()) {
                Box(Modifier.fillMaxWidth()
                    .weight(0.1f)) {
                    SearchTextField(
                        style = TextFieldStyle.Standard,
                        placeholder = stringResource(R.string.search_placeholder),
                        onClearSearch = {
                            viewModel.dispatchAction(HomeAction.QuerySearchUpdated(""))
                            viewModel.dispatchAction(HomeAction.Search)
                        },
                        onTextChange = {
                            viewModel.dispatchAction(HomeAction.QuerySearchUpdated(it))
                        },
                        onSearch = {
                            viewModel.dispatchAction(HomeAction.Search)
                        },
                        value = state.query
                    )
                    Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
                }
                Box(Modifier.fillMaxWidth()
                    .weight(0.9f)) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {

                        items(state.data) {
                            if (!state.isLoading && state.data.isNotEmpty()){
                                TransactionItem(it) {
                                    viewModel.dispatchAction(HomeAction.OpenDialogUpdateTransaction(it))
                                }
                                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                            }
                        }
                    }
                }
            }

            if (state.mustShowDialogCreate) {
                TransactionDialog(
                    isForCreate = true,
                    canAnnul = false,
                    onAction = { data ->
                        viewModel.dispatchAction(HomeAction.CreateTransaction(data))
                    },
                    onDismiss = {
                        viewModel.dispatchAction(HomeAction.CloseDialogCreateTransaction)
                    }
                )
            }


            state.selectedTransaction?.let {
                TransactionDialog(
                    isForCreate = false,
                    transactionUI = it.toUI(),
                    canAnnul = it.indStatus == Status.APPROVED,
                    onAction = { data ->
                        if (it.indStatus == Status.APPROVED) {
                            viewModel.dispatchAction(HomeAction.AnnulTransaction(data))
                        }
                    },
                    onDismiss = {
                        viewModel.dispatchAction(HomeAction.CloseDialogUpdateTransaction)
                    }
                )
            }

            state.errorResource?.let {
                LaunchedEffect(Unit) {
                    Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                    viewModel.dispatchAction(HomeAction.ResetError)
                }
            }
        }
    }
}
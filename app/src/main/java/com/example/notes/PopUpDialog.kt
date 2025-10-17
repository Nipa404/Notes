package com.example.notes

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpDialog(viewModel: NoteViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()


    if(state.popUpDialog) {
        BasicAlertDialog(
            onDismissRequest = {viewModel.closeDialog()},
        ) { }

    }

}
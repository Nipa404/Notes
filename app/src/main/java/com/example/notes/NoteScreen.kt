package com.example.notes

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: NoteViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    noteId: Int?
) {

    AskDeletePopUp()

    val state by viewModel.state.collectAsState()

    val currentId = state.noteNum

    LaunchedEffect(key1 = currentId) {
        viewModel.getOneNote(noteId)
    }

    if (state.toHome) {
        onNavigateBack()
    }

    DisposableEffect(Unit) {
        onDispose { viewModel.saveNote() }
    }

    Scaffold(

        floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.openDeletePopUp()
                    Log.d("del", state.noteNum.toString())



                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }

        },

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = state.title) },



                )

        }
    ) { innerPadding ->


        TextField(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            value = state.content,
            onValueChange = viewModel::onContentChange
        )

    }


}



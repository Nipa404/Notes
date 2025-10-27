package com.example.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteScreen(viewModel: NoteViewModel = koinViewModel(), onNavigateBack: () -> Unit, noteId: Int?) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.noteNum) {
        viewModel.getOneNote(noteId)
    }

    Box(modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {

        Row {
            Box(modifier =
                Modifier
                    .size(30.dp)
            ) {
                Text(text = state.content, color = Color.Red) 
            }

            FloatingActionButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back"
                    )

            }
        }

    }


}
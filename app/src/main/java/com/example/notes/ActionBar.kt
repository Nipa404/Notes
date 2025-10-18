package com.example.notes

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert


import androidx.compose.material3.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ActionBar(viewModel: NoteViewModel = koinViewModel(), onNavigateToNote: () -> Unit ) {
    PopUpDialog()
    val listOfNotes by viewModel.listOfNotes.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {viewModel.openDialog()}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add note"
                    )

            }
        },
        topBar = {
            TopAppBar(

//                navigationIcon = {
//                    IconButton(onClick = { TODO() }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//
//
//
//                    }
//                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.Black,
                ),

                title = {
                    Text(text = "Заметки")
                }
            )



        }

    ) { innerPadding ->
            LazyColumn(modifier =
                Modifier
                    .padding(innerPadding)




            ) {

                items(listOfNotes) {note ->

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()

                            .padding(6.dp)
                            .clickable(onClick = onNavigateToNote)
                            .background(Color.Red)
                            .height(50.dp)
                            .border(
                                2.dp,
                                color = Color.Black
                            )


                    ) {
                        Text(text = note.header, color = Color.Black, modifier = Modifier .padding(3.dp) .align(Alignment.Center))
                    }
                }

            }

    }


}














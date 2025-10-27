package com.example.notes

import android.R.attr.text
import android.icu.text.CaseMap
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpDialog(viewModel: NoteViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()


    if(state.popUpDialog) {
        BasicAlertDialog(
            onDismissRequest = { viewModel.closeDialog() },

        ) {
            OutlinedCard {
                Column {
                    Text(
                        text = "Add Title",
                        modifier = Modifier

                    )
                    Row {
                        OutlinedTextField(
                            value = state.title,
                            onValueChange = viewModel::onTitleChange
                        )

                        FloatingActionButton(onClick = { viewModel.addNote() }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Enter"
                            )

                        }

                    }


                }

                Column {
                    Text(
                        text = "Add Note",
                        modifier = Modifier
                    )

                    OutlinedTextField(
                        value = state.content,
                        onValueChange = viewModel::onContentChange
                    )
                }

            }





        }

    }

}
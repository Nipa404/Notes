package com.example.notes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AskDeletePopUp(viewModel: NoteViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()


    if (state.deletePopUp) {

        BasicAlertDialog(
            onDismissRequest = viewModel::closeDeletePopUp
        ) {

            OutlinedCard {
                Column {
                    Text(text = "Are you sure")
                    Row {

                        FloatingActionButton(onClick = {

                            viewModel.deleteNote()
                            viewModel.closeDeletePopUp()
                            viewModel.toHome()




                        }) { Text(text = "Yes") }

                        FloatingActionButton(onClick = viewModel::closeDeletePopUp) { Text(text = "No") }

                    }


                }


            }


        }


    }


}
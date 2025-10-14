package com.example.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NoteDao
) : ViewModel() {

    private val _state = MutableStateFlow(NoteState())




    fun onEvent(event: NoteEvents) {

        when(event) {

            is NoteEvents.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            NoteEvents.DeleteBox -> {
                _state.update { it.copy(laatikko = false) }
            }

        }



    }




}
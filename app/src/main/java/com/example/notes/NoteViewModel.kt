package com.example.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(private val repo: NoteRepository): ViewModel() {


    val listOfNotes: StateFlow<List<Note>> = repo.getAllNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _state = MutableStateFlow(NoteState())
    val state: StateFlow<NoteState> = _state


    fun openDialog() {
        _state.update { it.copy(popUpDialog = true) }
    }

    fun closeDialog() {
        _state.update { it.copy(popUpDialog = false) }
    }

    fun addNote() {
        _state.update { it.copy(popUpDialog = true) }
        val currentHeader = state.value.otsikko

        if(currentHeader == "") return

        viewModelScope.launch {
            repo.addNote(Note(header = currentHeader, body = ""))
        }

        _state.update { it.copy(teksti = "") }
        closeDialog()


    }



}
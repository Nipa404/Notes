package com.example.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
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

    fun onTitleChange(text: String){
        _state.update { it.copy(title = text) }
    }
    fun onContentChange(text: String){
        _state.update { it.copy(content = text) }
    }


    fun openDialog() {
        _state.update { it.copy(popUpDialog = true) }
    }

    fun closeDialog() {
        _state.update { it.copy(popUpDialog = false) }
    }

    fun saveNote() {

        val currentHeader = state.value.title
        val currentContent = state.value.content

        if (currentHeader == "") return

        viewModelScope.launch {
            repo.addNote(Note(title = currentHeader, content = currentContent))
        }
    }

    fun addNote() {

        val currentHeader = state.value.title
        val currentContent = state.value.content

        if(currentHeader == "") return

        viewModelScope.launch {
            repo.addNote(Note(title = currentHeader, content = currentContent))
        }

        _state.update { it.copy(
            title = "",
            content = "",
            popUpDialog = false
        ) }




    }






    fun noteNum(id: Int) {
        _state.update { it.copy(noteNum = id) }
    }

    fun getOneNote(id: Int?) {

        viewModelScope.launch {
            val note = repo.getOneNote(id)


            if(note != null) {

                _state.update {it.copy(
                    title = note.title,
                    content = note.content
                )}

            }


        }
    }







}
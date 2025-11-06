package com.example.notes

import android.util.Log
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

// AlertDialog stuff
    fun openDialog() {
        _state.update { it.copy(popUpDialog = true) }
    }

    fun closeDialog() {
        _state.update { it.copy(popUpDialog = false) }
    }

    fun openDeletePopUp() {
        _state.update { it.copy(deletePopUp = true) }
    }

    fun closeDeletePopUp() {
        _state.update { it.copy(deletePopUp = false) }
    }
    fun toHome() {//This takes user to homescreen when called
        _state.update { it.copy(toHome = true) }
    }

    fun saveNote() {//This function saves current changes to Note entity

        val currentTitle = state.value.title
        val currentContent = state.value.content
        val currentId = state.value.noteNum

        if (currentTitle == "") return

        viewModelScope.launch {
            repo.updateNote(Note(id = currentId ,title = currentTitle, content = currentContent))
        }
    }

    fun addNote() {//This function saves new note to Note entity

        val currentTitle = state.value.title
        val currentContent = state.value.content

        if(currentTitle == "") return

        viewModelScope.launch {
            repo.addNote(Note(title = currentTitle, content = currentContent))
        }

        _state.update { it.copy(
            title = "",
            content = "",
            popUpDialog = false
        ) }

    }

    fun deleteNote() {//Deletes a note from Note entity
        val currentTitle = state.value.title
        val currentContent = state.value.content
        val currentId = state.value.noteNum

        Log.d("del", currentId.toString())


        viewModelScope.launch {
            repo.deleteNote(Note(id = currentId, title = currentTitle, content = currentContent))
        }
    }



    fun noteNum(id: Int) {//Keeps track of what note user is on
        _state.update { it.copy(noteNum = id) }
    }

    fun getOneNote(id: Int?) {//Takes right data from Note entity using noteNum

        viewModelScope.launch {
            val note = repo.getOneNote(id)


            if(note != null) {

                _state.update {it.copy(
                    title = note.title,
                    content = note.content,
                    noteNum = note.id
                )}

            }


        }
    }







}
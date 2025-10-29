package com.example.notes



data class NoteState(

    val listOfNotes: List<Note> = emptyList(),
    val teksti: String = "",
    val title: String = "",
    val content: String = "",
    val noteNum: Int = 0,
    val popUpDialog: Boolean = false,
    val deletePopUp: Boolean = false,
    val delete: Boolean = false



)

package com.example.notes



data class NoteState(

    val listOfNotes: List<Note> = emptyList(),
    val teksti: String = "",
    val otsikko: String = "",
    val popUpDialog: Boolean = false



)

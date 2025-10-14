package com.example.notes

interface NoteEvents {

    data class DeleteNote(val note: Note): NoteEvents

    object DeleteBox: NoteEvents

}
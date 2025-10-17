package com.example.notes

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {

    suspend fun deleteNote(note: Note) = dao.deleteNote(note)

    suspend fun addNote(note: Note) = dao.addNote(note)

    fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()



}
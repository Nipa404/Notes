package com.example.notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert

@Dao
interface NoteDao {


    @Delete
    fun deleteNote(note: Note)




}
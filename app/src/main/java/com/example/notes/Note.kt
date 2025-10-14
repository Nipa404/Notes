package com.example.notes


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Note(
    val header: String,
    val body: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
package com.example.notes

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase








val koinModule = module {

    single<NoteDatabase> {
        Room.databaseBuilder(
            get(),
            NoteDatabase::class.java,
            "Notes.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<NoteDatabase>().noteDao() }
    single { NoteRepository(get() ) }
    single { NoteState(get()) }

    viewModel() { NoteViewModel(get()) }

}

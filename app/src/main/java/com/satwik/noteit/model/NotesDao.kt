package com.satwik.noteit.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes ORDER BY notes_id DESC")
    fun getNote(): LiveData<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: NotesEntity)

    @Query("DELETE FROM Notes WHERE notes_id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: NotesEntity)
}
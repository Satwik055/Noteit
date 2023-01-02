package com.satwik.noteit.repository

import androidx.lifecycle.LiveData
import com.satwik.noteit.model.NotesDao
import com.satwik.noteit.model.NotesEntity

class MainRepository(private val dao:NotesDao) {

    fun getNote(): LiveData<List<NotesEntity>> {
        return dao.getNote()
    }

    fun insertNotes(notes: NotesEntity){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: NotesEntity){
        dao.updateNotes(notes)
    }


}
package com.satwik.noteit.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.satwik.noteit.databinding.ActivityCreateNoteBinding
import com.satwik.noteit.model.NotesEntity
import com.satwik.noteit.viewmodel.MainViewModel

class CreateActivity : AppCompatActivity() {

    private val mainViewModel:MainViewModel by viewModels()
    private lateinit var binding: ActivityCreateNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

//------------------------------------------------Functions--------------------------------------------------//
    fun toHome(view:View){
        finish()
    }

    fun createNote(view:View){
        if (binding.editTextContent.text.toString().isEmpty()){
            Toast.makeText(this, "Note content is empty", Toast.LENGTH_SHORT).show()
        }
        else{
            val title = binding.editTextHeading.text.toString()
            val content = binding.editTextContent.text.toString()
            val data = NotesEntity(null, title, content)
            mainViewModel.insertNotes(data)
            finish()
        }

    }




}
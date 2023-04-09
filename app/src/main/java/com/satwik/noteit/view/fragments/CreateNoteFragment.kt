package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentCreateNoteBinding
import com.satwik.noteit.model.NotesEntity
import com.satwik.noteit.utils.KeyboardUtil
import com.satwik.noteit.viewmodel.MainViewModel

class CreateNoteFragment : Fragment() {
    lateinit var binding: FragmentCreateNoteBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        //Back button
        binding.btnback.setOnClickListener{
            findNavController().navigate(R.id.action_createNoteFragment_to_homeFragment)

        }

        //SaveButton
        binding.saveButton.setOnClickListener{
            if (binding.editTextContent.text.toString().isEmpty()){
                Toast.makeText(activity, "Note content is empty", Toast.LENGTH_SHORT).show()

            }
            else{
                val title = binding.editTextHeading.text.toString()
                val content = binding.editTextContent.text.toString()
                val data = NotesEntity(null, title, content)
                mainViewModel.insertNotes(data)
                findNavController().navigate(R.id.action_createNoteFragment_to_homeFragment)
            }
        }

        return binding.root
    }
    //Opening keyboard as user enters in EditNoteFragment
    override fun onStart() {
        super.onStart()
        KeyboardUtil.showSoftKeyboard(binding.editTextHeading)
    }
}
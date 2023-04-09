package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentEditNoteBinding
import com.satwik.noteit.model.NotesEntity
import com.satwik.noteit.utils.KeyboardUtil
import com.satwik.noteit.viewmodel.MainViewModel

class EditNoteFragment : Fragment() {
    private lateinit var binding: FragmentEditNoteBinding
    private val notes by navArgs<EditNoteFragmentArgs>()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View {
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        //EditText
        binding.editTextHeading.text =
            Editable.Factory.getInstance().newEditable(notes.editableData.title)
        binding.editTextContent.text =
            Editable.Factory.getInstance().newEditable(notes.editableData.content)

        //BackButton
        binding.btnback.setOnClickListener {
            findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
        }

        //SaveButton
        binding.btnSave.setOnClickListener {
            val title = binding.editTextHeading.text.toString()
            val content = binding.editTextContent.text.toString()
            val id = notes.editableData.id
            val editedData = NotesEntity(id, title, content)
            mainViewModel.updateNotes(editedData)
            findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
        }

        return binding.root
    }

    //Opening keyboard as user enters in EditNoteFragment
    override fun onStart() {
        super.onStart()
        KeyboardUtil.showSoftKeyboard(binding.editTextContent)
    }

}


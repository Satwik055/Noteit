package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View { binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        //EditText
        binding.editTextHeading.text =
            Editable.Factory.getInstance().newEditable(notes.editableData.title)
        binding.editTextContent.text =
            Editable.Factory.getInstance().newEditable(notes.editableData.content)

//--------------------------------------------Toolbar--------------------------------------------------//
        binding.toolbarEditnote.setOnMenuItemClickListener {
            when (it.itemId) {

                //Confirm Button
                R.id.editnote_toolbar_options_confirm -> {
                    saveEditedNote()
                    true
                }

                //Default
                else -> {true}
            }
        }

        //Navigation Button (Back Button)
        binding.toolbarEditnote.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
//-----------------------------------------------------------------------------------------------------//

        //Setting up initial character count
        var characterCount = binding.editTextContent.text.length.toString()
        binding.textViewCounter.text = "$characterCount Characters"

        //Live Character counter
        binding.editTextContent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //pass
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.textViewCounter.text = binding.editTextContent.text.length.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var characterCount = binding.editTextContent.text.length.toString()
                binding.textViewCounter.text = "$characterCount Characters"
            }
        })



        return binding.root
    }


    private fun saveEditedNote() {
        val title = binding.editTextHeading.text.toString()
        val content = binding.editTextContent.text.toString()
        val id = notes.editableData.id
        val editedData = NotesEntity(id, title, content)
        mainViewModel.updateNotes(editedData)
        findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    //Opens keyboard as user enters in EditNoteFragment
    override fun onStart() {
        super.onStart()
        KeyboardUtil.showSoftKeyboard(binding.editTextContent)
    }

}


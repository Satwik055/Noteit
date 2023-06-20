package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentCreateNoteBinding
import com.satwik.noteit.model.NotesEntity
import com.satwik.noteit.utils.CalendarUtil
import com.satwik.noteit.utils.KeyboardUtil
import com.satwik.noteit.viewmodel.MainViewModel

class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View { binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)


//--------------------------------------------Toolbar--------------------------------------------------//
        binding.toolbarCreatenote.setOnMenuItemClickListener {
            when (it.itemId) {

                //Confirm button
                R.id.createnote_toolbar_options_confirm -> {
                    saveNewNote()
                    true
                }
                //Tag button (Opens Tag bottom sheet)
                R.id.createnote_toolbar_options_tag -> {
                    findNavController().navigate(R.id.action_createNoteFragment_to_tagBottomSheetFragment)
                    true
                }

                //Default
                else -> { true }
            }
        }

        //Navigation Button (Back Button)
        binding.toolbarCreatenote.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
//---------------------------------------------------------------------------------------------------//

        //Current Date and time
        binding.textViewDateandtime.text = getString(R.string.label_textView_dateAndTime, CalendarUtil.getCurrentDay(), CalendarUtil.getCurrentTime() )

        //Live Character Counter
        binding.editTextContent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //pass
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Pass
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.textViewCounter.text = getString(R.string.label_textView_counter, binding.editTextContent.text.length.toString())
            }
        })






        return binding.root
    }

    private fun saveNewNote() {
        if (binding.editTextContent.text.toString().isEmpty()) {
            Toast.makeText(activity, "Note content is empty", Toast.LENGTH_SHORT).show()

        }
        else {
            val title = binding.editTextHeading.text.toString()
            val content = binding.editTextContent.text.toString()
            val lastEditedDate = CalendarUtil.getCurrentDate()
            val lastEditedDay = CalendarUtil.getCurrentDay()
            val lastEditedTime = CalendarUtil.getCurrentTime()
            val lastEditedMonth = CalendarUtil.getCurrentMonth()
            val tags = getDataFromCurrentBackstack<List<String>>("SELECTED_CHIP_TEXT")?.joinToString(",")
            val data = NotesEntity(null, title, content, lastEditedDate, lastEditedDay, lastEditedTime, lastEditedMonth, tags)
            mainViewModel.insertNotes(data)
            findNavController().popBackStack()
        }
    }

    //Opening keyboard as user enters in EditNoteFragment
    override fun onStart() {
        super.onStart()
        KeyboardUtil.showSoftKeyboard(binding.editTextHeading)
    }

    /**
     * Returns data (if any) of type T from the current backstack using the specified key
     * @param key
     * The key used to retrieve data from current back stack
     * @param T
     * Type of the data to retrieve
     */
    private fun <T> getDataFromCurrentBackstack(key:String): T? {
        return findNavController().currentBackStackEntry?.savedStateHandle?.get<T>(key)
    }


}
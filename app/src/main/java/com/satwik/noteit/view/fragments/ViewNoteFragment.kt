package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentViewNoteBinding
import com.satwik.noteit.view.custom.NoteitAlertDialog
import com.satwik.noteit.viewmodel.MainViewModel

class ViewNoteFragment : Fragment() {
    private lateinit var binding: FragmentViewNoteBinding
    private val notes by navArgs<ViewNoteFragmentArgs>()
    private val mainViewModel: MainViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View { binding = FragmentViewNoteBinding.inflate(layoutInflater, container, false)

        //TextViews
        binding.editTextHeading.text = notes.data.title
        binding.editTextContent.text = notes.data.content
        binding.textViewDateandtime.text = getString(R.string.label_textView_dateAndTime, notes.data.lastEditedDay, notes.data.lastEditedTime)


 //--------------------------------------------Toolbar--------------------------------------------------//
        /*
        -----Toolbar icon Documentation-----

        * Navigation button icon is getting setup directly from toolbar component inside fragment_view_note.xml
        *
        * Action button icon is getting setup from editnote_fragment_menu.xml inside 'res/menu'  directory
        *
        * Custom Overflow menu is getting setup from styles.xml inside 'res/values' directory
        * */


        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {

                //Edit Button
                R.id.toolbar_options_edit -> {
                    findNavController().navigate(ViewNoteFragmentDirections.actionViewNoteFragmentToEditNoteFragment(notes.data))
                    true
                }

                //Delete Button (in overflow menu)
                R.id.menu_options_delete->{
                    showAlertDialog()
                    true
                }

                //Default
                else -> {true}
            }
        }

        //Navigation Button (Back Button)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

//----------------------------------------------------------------------------------------------------//

        //Character counter
        binding.textViewCounter.text = getString(R.string.label_textView_counter, binding.editTextContent.text.length.toString())



        return binding.root
    }

    private fun showAlertDialog() {
        val dialog = NoteitAlertDialog(requireContext())
        dialog.setTitle("Delete note ?")
        dialog.setMessage("Notes once deleted cant be recovered")
        dialog.setPositiveButton("Confirm", View.OnClickListener {
            mainViewModel.deleteNotes(notes.data.id!!)
            findNavController().popBackStack()
        })
        dialog.setNegativeButton("Cancel", null)
        dialog.show()

    }

}



package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentViewNoteBinding
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
                    openBottomSheetDialog()
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


        return binding.root
    }

    private fun openBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.fragment_bottom_sheet)
        dialog.show()

        val btnDelete = dialog.findViewById<Button>(R.id.btn_bottomfrag_delete)
        val btnCancel = dialog.findViewById<Button>(R.id.btn_bottomfrag_cancel)

        //Delete Button
        btnDelete?.setOnClickListener {
            mainViewModel.deleteNotes(notes.data.id!!)
            dialog.hide()
            findNavController().popBackStack()
        }

        //Cancel Button
        btnCancel?.setOnClickListener {
            dialog.hide()
        }
    }

}



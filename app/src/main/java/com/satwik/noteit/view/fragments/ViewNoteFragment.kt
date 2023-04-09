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

        //BackButton
        binding.btnback.setOnClickListener {
            findNavController().navigate(R.id.action_viewNoteFragment_to_homeFragment)
        }

        //EditNote Button
        binding.btnEditnotes.setOnClickListener {
            val action = ViewNoteFragmentDirections.actionViewNoteFragmentToEditNoteFragment(notes.data)
            findNavController().navigate(action)
        }

        //Move-To-Trash Button
        binding.btnMvToTrash.setOnClickListener {
            mainViewModel.deleteNotes(notes.data.id!!)
            findNavController().navigate(R.id.action_viewNoteFragment_to_homeFragment)
        }

        return binding.root
    }
}
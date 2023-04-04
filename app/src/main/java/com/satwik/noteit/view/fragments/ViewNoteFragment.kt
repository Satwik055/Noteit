package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentViewNoteBinding

class ViewNoteFragment : Fragment() {
    private lateinit var binding: FragmentViewNoteBinding
    private val notes by navArgs<ViewNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View {
        binding = FragmentViewNoteBinding.inflate(layoutInflater, container, false)

        //Setting up the textview with the parsed data
        binding.editTextHeading.text = notes.data.title.toString()
        binding.editTextContent.text = notes.data.content.toString()

        //BackButton
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewNoteFragment_to_homeFragment)
        }

        //EditNote Button
        binding.btnEditnotes.setOnClickListener {
          findNavController().navigate(R.id.action_viewNoteFragment_to_editNoteFragment)

        }

        return binding.root
    }
}
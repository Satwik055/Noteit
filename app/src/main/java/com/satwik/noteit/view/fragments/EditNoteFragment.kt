package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.satwik.noteit.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {
    lateinit var binding: FragmentEditNoteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View {
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}
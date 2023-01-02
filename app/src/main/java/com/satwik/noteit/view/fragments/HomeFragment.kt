package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentHomeBinding
import com.satwik.noteit.view.adapter.MyNotesAdapter
import com.satwik.noteit.viewmodel.MainViewModel


class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        mainViewModel.getNote().observe(viewLifecycleOwner) { notesList ->
            binding.myNotesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                binding.myNotesRecyclerView.adapter = MyNotesAdapter(requireContext(), notesList)
        }














        return binding.root
    }

}
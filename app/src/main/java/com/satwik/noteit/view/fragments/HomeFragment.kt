package com.satwik.noteit.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentHomeBinding
import com.satwik.noteit.model.NotesEntity
import com.satwik.noteit.utils.KeyboardUtil
import com.satwik.noteit.view.adapter.MyNotesAdapter
import com.satwik.noteit.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private val mainViewModel:MainViewModel by viewModels()
    private var oldMyNotes = arrayListOf<NotesEntity>()
    private lateinit var adapter:MyNotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View { binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        //FAB listener
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        //Setting up Recycler View
        mainViewModel.getNote().observe(viewLifecycleOwner) { notesList ->

            //Showing illustration and instruction if user hasn't created any notes
            if(notesList.isEmpty()){
                binding.imageViewIllustration.visibility = View.VISIBLE
                binding.textViewInstructions.visibility = View.VISIBLE
            }
            else{
                oldMyNotes = notesList as ArrayList<NotesEntity>
                binding.myNotesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
                adapter =  MyNotesAdapter(requireContext(), notesList)
                binding.myNotesRecyclerView.adapter = adapter
            }
        }

        //Searchbar
        binding.editTextSearch.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //pass
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Pass
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try{
                    notesFiltering(s)
                }
                catch(e:Exception){
                    Log.e("Error", e.toString())
                }

            }
        })

        //Handling searchbar focus
        binding.editTextSearch.setOnClickListener {
            if(binding.editTextSearch.isFocused){
                binding.editTextSearch.clearFocus()
                KeyboardUtil.hideSoftKeyboard(binding.editTextSearch)
            }
        }



        return binding.root
    }

    private fun notesFiltering(s:CharSequence?) {
        val newFilteredList = arrayListOf<NotesEntity>()
        for (i in oldMyNotes){
            if(i.title.contains(s.toString()) || i.content.contains(s.toString())) {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }






}
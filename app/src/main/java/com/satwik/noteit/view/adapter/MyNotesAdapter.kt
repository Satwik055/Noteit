package com.satwik.noteit.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.satwik.noteit.R
import com.satwik.noteit.databinding.CardlayoutLargeBinding
import com.satwik.noteit.databinding.LargecardviewBinding
import com.satwik.noteit.model.NotesEntity
import com.satwik.noteit.view.fragments.HomeFragmentDirections

class MyNotesAdapter(val requireContext: Context, private val notesList: List<NotesEntity>) :RecyclerView.Adapter<MyNotesAdapter.MynotesViewholder>() {
    class MynotesViewholder(val binding:LargecardviewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MynotesViewholder {
        return MynotesViewholder(LargecardviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MynotesViewholder, position: Int) {
        val data = notesList[position]

        //Sets up content as title if user doesn't provides a title
        if ( data.title.isEmpty()){
            holder.binding.largeCardTitle.text = data.content
            holder.binding.largeCardContent.text = data.content
        }
        else{
            holder.binding.largeCardTitle.text = data.title
            holder.binding.largeCardContent.text = data.content
        }

        holder.binding.root.setOnClickListener{
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToViewNoteFragment(data))
        }

    }

    override fun getItemCount() = notesList.size

}
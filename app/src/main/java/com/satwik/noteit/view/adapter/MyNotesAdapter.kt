package com.satwik.noteit.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satwik.noteit.databinding.CardlayoutLargeBinding
import com.satwik.noteit.databinding.LargecardviewBinding
import com.satwik.noteit.model.NotesEntity

class MyNotesAdapter(val requireContext: Context, private val notesList: List<NotesEntity>) :RecyclerView.Adapter<MyNotesAdapter.MynotesViewholder>() {
    class MynotesViewholder(val binding:LargecardviewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MynotesViewholder {
        return MynotesViewholder(LargecardviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MynotesViewholder, position: Int) {

        //Sets up content as title if user doesn't provides a title
        if ( notesList[position].title.isEmpty()){
            holder.binding.largeCardTitle.text = notesList[position].content
            holder.binding.largeCardContent.text = notesList[position].content
        }
        else{
            holder.binding.largeCardTitle.text = notesList[position].title
            holder.binding.largeCardContent.text = notesList[position].content
        }

    }

    override fun getItemCount() = notesList.size

}
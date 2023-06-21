package com.satwik.noteit.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.satwik.noteit.R
import com.satwik.noteit.databinding.LargecardviewBinding
import com.satwik.noteit.model.NotesEntity
import com.satwik.noteit.view.fragments.HomeFragmentDirections

class MyNotesAdapter(private val requireContext: Context, private var notesList: List<NotesEntity>) :RecyclerView.Adapter<MyNotesAdapter.MynotesViewholder>() {

    fun filtering(newFilteredList: ArrayList<NotesEntity>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
    class MynotesViewholder(val binding:LargecardviewBinding) : RecyclerView.ViewHolder(binding.root)

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

        //Sets up card date and month
        holder.binding.largeCardLastEditedDateAndMonth.text = requireContext.resources.getString(R.string.label_card_dateAndMonth, data.lastEditedDate, data.lastEditedMonth)

        //Card click-listener
        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToViewNoteFragment(data)
            findNavController(it).navigate(action)
        }

        //Card Tag
        if(data.tags != emptyList<String>()){
            holder.binding.textViewTags.text = data.tags!!.first()
        }

    }

    override fun getItemCount() =
        notesList.size

    private fun stringToList(string:String?):List<String>{
        return listOf(*string?.split(",")!!.toTypedArray())
    }

    private fun onlyFirstTag(string:String):String{
        val list = listOf(*string.split(",").toTypedArray())
        return list.elementAt(1)
    }

}
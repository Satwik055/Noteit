package com.satwik.noteit.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.satwik.noteit.R
import com.satwik.noteit.databinding.FragmentTagBottomSheetBinding

class TagBottomSheetFragment :BottomSheetDialogFragment() {
    private lateinit var binding: FragmentTagBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View {binding = FragmentTagBottomSheetBinding.inflate(layoutInflater, container, false)


        //Plus(+) button of CreateNewTag editText
        binding.btnCreateNewTag.setOnClickListener {
            val chipText = binding.etCreateNewTag.text.toString()
            if (chipText.isNotEmpty()) {
                createNewChip(binding.chipGroup, chipText)
                binding.etCreateNewTag.text.clear()
            }
        }

        //Save tag button
        binding.btnSaveTag.setOnClickListener {
            val selectedChipText = getSelectedChipText(binding.chipGroup)
            if(selectedChipText.isNotEmpty()){
                sendDataToPreviousBackstack("SELECTED_CHIP_TEXT", selectedChipText)
                }
            findNavController().popBackStack()
        }



        return binding.root
    }


    private fun createNewChip(chipGroup: ChipGroup, chipText:String ) {
        val chip = Chip(context)
        chip.id = generateViewId()
        chip.text = chipText
        chip.isCheckable = true
        chipGroup.addView(chip)
    }

    private fun getSelectedChipText(chipGroup:ChipGroup):List<String>{
        val selectedChipTexts = ArrayList<String>()
        for(i in 0 until chipGroup.childCount){
            val chip = chipGroup.getChildAt(i) as Chip
            if (chip.isChecked){
                selectedChipTexts.add(chip.text.toString())
            }
        }
        return selectedChipTexts
    }

    /**
     * Sends data to previous destination using provided key
     * @param key
     * The key to associate with the data
     * @param data
     * data to be sent to previous backstack
     */
    private fun <T> sendDataToPreviousBackstack(key: String, data:T){
        findNavController().previousBackStackEntry?.savedStateHandle?.set(key,data)
    }


}
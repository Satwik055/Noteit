package com.satwik.noteit.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

        //Create tag editText plus (+) button click listener
        binding.btnCreateNewTag.setOnClickListener {
            val chipText =  binding.etCreateNewTag.text.toString()
            if (chipText.isEmpty()) {
                //Do Nothing
            } else {
                createNewChip(binding.chipGroup, chipText)
                binding.etCreateNewTag.text?.clear()
            }
        }




        return binding.root
    }

    private fun createNewChip(chipGroup: ChipGroup, chipText:String ) {
        val chip = Chip(context)
        chip.text = chipText
        chip.isCheckable = true
        chipGroup.addView(chip)
    }


}
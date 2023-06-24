package com.satwik.noteit.view.custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.satwik.noteit.R

class NoteitAlertDialog(context:Context):Dialog(context) {

    private var title = ""
    private var message = ""
    private var positiveButtonText = ""
    private var negativeButtonText = ""
    private var positiveButtonClickListener: View.OnClickListener? = null
    private var negativeButtonClickListener: View.OnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.modal_dialog)

        val tvTitle:TextView = findViewById(R.id.tvTitle)
        val tvMessage:TextView = findViewById(R.id.tvMessage)
        val btnPositive:Button = findViewById(R.id.btnPositive)
        val btnNegative:Button = findViewById(R.id.btnNegative)

        tvTitle.text = title
        tvMessage.text = message
        btnPositive.text = positiveButtonText
        btnNegative.text = negativeButtonText

        btnPositive.setOnClickListener {
            positiveButtonClickListener?.onClick(it)
            dismiss()
        }

        btnNegative.setOnClickListener {
            negativeButtonClickListener?.onClick(it)
            dismiss()
        }
    }



    fun setTitle(title:String){
        this.title = title
    }

    fun setMessage(message:String){
        this.message = message
    }

    fun setPositiveButton(buttonText:String, listener:View.OnClickListener?){
        this.positiveButtonText = buttonText
        this.positiveButtonClickListener = listener
    }

    fun setNegativeButton(buttonText:String, listener: View.OnClickListener?){
        this.negativeButtonText = buttonText
        this.negativeButtonClickListener = listener
    }


}
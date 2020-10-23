package com.heptagon.qanda.exam.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heptagon.qanda.databinding.*
import com.heptagon.qanda.internal.Utility
import com.heptagon.qanda.internal.Utility.EMAILTEXT
import com.heptagon.qanda.internal.Utility.IMAGE
import com.heptagon.qanda.internal.Utility.MULTISELECT
import com.heptagon.qanda.internal.Utility.RADIO
import com.heptagon.qanda.internal.Utility.TEXT
import com.heptagon.qanda.exam.network.response.Question


class TestAdapter(private var mQuestions: List<Question>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            TEXT -> TextViewHolder(QuestionTextBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            EMAILTEXT -> EmailTextHolder(QuestionEmailTextBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            RADIO -> RadioHolder(QuestionRadioBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            MULTISELECT -> MultiSelectHolder(
                QuestionMultiSelectBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            IMAGE -> ImageHolder(QuestionImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> TextViewHolder(QuestionTextBinding.inflate(LayoutInflater.from(parent.context)))
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextViewHolder -> holder.bindTextView(mQuestions[position])
            is MultiSelectHolder -> holder.bindMultiSelectView(mQuestions[position])
            is RadioHolder -> holder.bindRadioView(mQuestions[position])
            is EmailTextHolder -> holder.bindEmailTextView(mQuestions[position])
            is ImageHolder ->  holder.bindImageView(mQuestions[position])
        }
    }

    override fun getItemCount(): Int {

        return mQuestions.size
    }

    override fun getItemViewType(position: Int): Int =
        when (Utility.QuestionType.map[mQuestions[position].type]) {
            is Utility.QuestionType.Text -> TEXT
            is Utility.QuestionType.Radio -> RADIO
            is Utility.QuestionType.Emailtext -> EMAILTEXT
            is Utility.QuestionType.MultiSelect -> MULTISELECT
            is Utility.QuestionType.Image -> IMAGE


            else -> TEXT
        }


}

class TextViewHolder(private val questionTextBinding: QuestionTextBinding) :
    RecyclerView.ViewHolder(questionTextBinding.root) {
    fun bindTextView(question: Question) {
        questionTextBinding.tvTextQuestion1.text = question.question
    }
}

class RadioHolder(private val questionRadioBinding: QuestionRadioBinding) :
    RecyclerView.ViewHolder(questionRadioBinding.root) {
    fun bindRadioView(question: Question) {
        questionRadioBinding.textView.text = question.question
        for(choice in question.values) {
            val radiobutton=RadioButton(questionRadioBinding.root.context)
            radiobutton.text=choice.value
            questionRadioBinding.radioLayout.addView(radiobutton)
        }
    }
}

class MultiSelectHolder(private val questionMultiSelectBinding: QuestionMultiSelectBinding) :
    RecyclerView.ViewHolder(questionMultiSelectBinding.root) {
    fun bindMultiSelectView(question: Question) {
        questionMultiSelectBinding.textView2.text = question.question
        for(choice in question.values) {
            val checkbox=CheckBox(questionMultiSelectBinding.root.context)
            checkbox.text=choice.value
            questionMultiSelectBinding.layoutMultiOption.addView(checkbox)
        }
    }
}

class EmailTextHolder(private val questionEmailTextBinding: QuestionEmailTextBinding) :
    RecyclerView.ViewHolder(questionEmailTextBinding.root) {
    fun bindEmailTextView(question: Question) {
        questionEmailTextBinding.tvEmailQuestion.text = question.question

    }

}
class ImageHolder(private val questionImageBinding: QuestionImageBinding) :
    RecyclerView.ViewHolder(questionImageBinding.root) {
    fun bindImageView(question: Question) {
        questionImageBinding.tvImageQuestion.text = question.question
        Glide.with(itemView.context).load(question.url).into(questionImageBinding.imageView)
    }
}
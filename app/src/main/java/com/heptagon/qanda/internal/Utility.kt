package com.heptagon.qanda.internal

object Utility {
    const val TEXT = 1
    const val EMAILTEXT = 2
    const val RADIO = 3
    const val MULTISELECT = 4
    const val IMAGE = 5

    sealed class QuestionType(val type: String) {
        class Text : QuestionType("text")
        class Emailtext : QuestionType("emailtext")
        class Radio : QuestionType("radio")
        class MultiSelect : QuestionType("multiselect")
        class Image : QuestionType("imageview")
        companion object {
            val map = hashMapOf(
                "text" to Text(),
                "emailtext" to Emailtext(),
                "radio" to Radio(),
                "multiselect" to MultiSelect(),
                "imageview" to Image()
            )
        }

    }
}
package com.heptagon.qanda.exam.network.response

data class TestResponse(
    val course_process_id: Int,
    val questions: List<Question>,
    val status: Boolean
)

data class Question(
    val answers: String,
    val error_message: String,
    val image_flag: Int,
    val images: List<String>,
    val mandatory: Int,
    val multi_option_flag: Int,
    val multi_select_flag: Int,
    val placeholder: String,
    val question: String,
    val question_id: Int,
    val type: String,
    val url: String,
    val values: List<Value>
)

data class Value(
    val id: Int,
    val image_url: String,
    val selected: Int,
    val value: String
)
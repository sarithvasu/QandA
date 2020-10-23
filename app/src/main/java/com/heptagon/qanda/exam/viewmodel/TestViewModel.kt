package com.heptagon.qanda.exam.viewmodel

import androidx.lifecycle.ViewModel
import com.heptagon.qanda.internal.lazyDeferred
import com.heptagon.qanda.exam.repository.TestRepository

class TestViewModel(private val testRepository: TestRepository, private val queryParameterMap: Map<String, String>) : ViewModel() {
    val testResponse by lazyDeferred {
        testRepository.getTest(queryParameterMap)
    }
}
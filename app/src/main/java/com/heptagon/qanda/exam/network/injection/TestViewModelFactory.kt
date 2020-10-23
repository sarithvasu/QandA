package com.heptagon.qanda.exam.network.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dinerindia.user.network.injection.TestViewModelTypeEnum
import com.heptagon.qanda.internal.RepositoryInstance
import com.heptagon.qanda.exam.viewmodel.TestViewModel


class UserViewModelFactory(private val viewModelType: TestViewModelTypeEnum, private val queryParameterMap: Map<String, String>) : ViewModelProvider.NewInstanceFactory(){



    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (viewModelType) {

            TestViewModelTypeEnum.TestViewModel -> {
                TestViewModel(RepositoryInstance.testRepositoryInstance,queryParameterMap) as T
            }

        }
    }
}







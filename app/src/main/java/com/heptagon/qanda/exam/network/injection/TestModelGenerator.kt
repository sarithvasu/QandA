package com.heptagon.qanda.exam.network.injection

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dinerindia.user.network.injection.TestViewModelTypeEnum
import com.heptagon.qanda.exam.viewmodel.TestViewModel

class TestModelGenerator : ViewModel() {

    companion object {
        private lateinit var viewModel: ViewModel

        fun getModel(fragment: Fragment, testViewModelType: TestViewModelTypeEnum, queryParameterMap: Map<String, String> ): ViewModel {

            viewModel = when (testViewModelType) {
                TestViewModelTypeEnum.TestViewModel -> {
                    val modelFactory = UserViewModelFactory(TestViewModelTypeEnum.TestViewModel, queryParameterMap)
                    ViewModelProvider(fragment,modelFactory).get(TestViewModel::class.java)
                }
            }

            return viewModel

        }



    }
}
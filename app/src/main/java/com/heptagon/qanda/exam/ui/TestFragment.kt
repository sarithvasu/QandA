package com.heptagon.qanda.exam.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dinerindia.user.network.injection.TestViewModelTypeEnum
import com.heptagon.qanda.databinding.TestFragmentBinding
import com.heptagon.qanda.internal.ScopedFragment
import com.heptagon.qanda.exam.network.injection.TestModelGenerator
import com.heptagon.qanda.exam.ui.adapters.TestAdapter
import com.heptagon.qanda.exam.viewmodel.TestViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TestFragment : ScopedFragment() ,View.OnClickListener {


    private lateinit var mTestAdapter: TestAdapter
    private lateinit var mRootView: TestFragmentBinding
    private lateinit var mViewModel: TestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView=TestFragmentBinding.inflate(inflater)
        return mRootView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val queryParameterMap = mapOf("indent" to "2")
        mViewModel = TestModelGenerator.getModel(
            this,
            TestViewModelTypeEnum.TestViewModel, queryParameterMap
        ) as TestViewModel
        initViews()
        getTestDataApi()
    }

    private fun initViews() {
        mRootView.srlTest.setOnRefreshListener { getTestDataApi() }
        mRootView.rvQuestionAnswer.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        mRootView.btnSubmit.setOnClickListener(this)
    }

    private fun getTestDataApi(): Job = launch(Dispatchers.Main) {
        try {


            val testResponse = mViewModel.testResponse.await()

            testResponse.observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer
                mRootView.srlTest.isRefreshing = false
                mRootView.progressBar.visibility=GONE
                mTestAdapter=TestAdapter(it.questions)
                mRootView.rvQuestionAnswer.adapter=mTestAdapter
                //mTestAdapter.submitRequestDishData(it.questions as ArrayList<Question>)



            })
        } catch (e: Exception) {
            Log.e("","$e")

        }
    }

    override fun onClick(v: View?) {
        Toast.makeText(requireContext(),"Test Submitted",Toast.LENGTH_SHORT).show()
    }

}
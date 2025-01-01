package com.chenliang.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Adam
 * 2025-01-01
 */
open abstract class MyBaseActivity<Binding : ViewDataBinding, VModel : ViewModel> :
    AppCompatActivity() {

    lateinit var binding: Binding
    lateinit var viewModel: VModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<Binding>(this, layoutId())
        viewModel = createViewModel(initViewModelClass())
        initCreate()
        initView()
    }

    abstract fun layoutId(): Int
    abstract fun initCreate()
    abstract fun initView()
    abstract fun initViewModelClass(): Class<VModel>


    private fun createViewModel(c: Class<VModel>): VModel {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(c)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

}
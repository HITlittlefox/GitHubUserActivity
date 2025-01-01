package com.example.githubapi.act

import android.widget.Toast
import com.chenliang.library.base.MyBaseActivity
import com.example.githubapi.R
import com.example.githubapi.databinding.*
import com.example.githubapi.viewmodel.PruductListViewModel

class MainActivity : MyBaseActivity<ActivityMainBinding, PruductListViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_main;
    }


    override fun initViewModelClass(): Class<PruductListViewModel> {
        return PruductListViewModel::class.java
    }

    override fun initCreate() {

    }

    override fun initView() {
        binding.button1.setOnClickListener {
            Toast.makeText(this, "what button", Toast.LENGTH_SHORT).show()

        }
    }
}
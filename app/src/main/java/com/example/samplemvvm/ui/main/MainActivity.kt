package com.example.samplemvvm.ui.main

import com.example.samplemvvm.R
import com.example.samplemvvm.base.BaseActivity

class MainActivity : BaseActivity<com.example.samplemvvm.databinding.ActivityMainBinding, MainState, MainViewModel>(R.layout.activity_main) {
    override fun init() {
        viewModel.login("QuanLA4@fsoft.com.vn")
    }

    override val viewModel: MainViewModel
        get() = TODO("Not yet implemented")

    override fun onStateChange(state: MainState) {
        when (state) {
            is MainState.DefaultState -> {

            }
            is MainState.ShowLoading -> {

            }
            is MainState.HideLoading -> {

            }
            is MainState.LoginSuccess -> {

            }
            is MainState.LoginFail -> {

            }
            else -> {}
        }
    }

}
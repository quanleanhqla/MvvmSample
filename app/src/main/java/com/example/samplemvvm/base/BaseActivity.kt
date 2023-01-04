package com.example.samplemvvm.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.samplemvvm.iview.IViewModel
import com.example.samplemvvm.iview.IViewState

abstract class BaseActivity<B: ViewDataBinding, VS: IViewState, VM: IViewModel<VS>>(
    @LayoutRes
    private val layoutId: Int
): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initObservers()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(stateObserver != null) {
            viewModel.viewState.removeObserver(stateObserver!!)
        }
    }


    abstract fun init()

    protected abstract val viewModel: VM
    protected abstract fun onStateChange(state: VS)

    private var stateObserver: Observer<VS>? = null

    private fun initObservers() {
        if(stateObserver == null) {
            stateObserver = Observer<VS> {state -> onStateChange(state)}
        }
        viewModel.viewState.observe(this, stateObserver!!)
    }

    protected fun showLoading() {}
    protected fun hideLoading() {}
}
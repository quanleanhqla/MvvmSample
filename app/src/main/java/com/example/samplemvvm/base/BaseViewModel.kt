package com.example.samplemvvm.base

import androidx.lifecycle.ViewModel
import com.example.samplemvvm.iview.IViewModel
import com.example.samplemvvm.iview.IViewState
import com.example.samplemvvm.utils.CommonViewState
import com.example.samplemvvm.utils.SingleLiveEvent
import com.example.samplemvvm.utils.SingleMediatorEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable


abstract class BaseViewModel<VS: IViewState>: ViewModel(), IViewModel<VS> {
    protected val compositeDisposable = CompositeDisposable()

    override val viewState = SingleMediatorEvent<VS>()
    override val commonViewState = SingleLiveEvent<CommonViewState>()

    override var state: VS
        get() = viewState.value ?: initialState
        set(value) = viewState.setValue(value)

    override fun showLoading() {
        commonViewState.value = CommonViewState.showLoading
    }

    override fun hideLoading() {
        commonViewState.value = CommonViewState.hideLoading
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
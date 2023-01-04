package com.example.samplemvvm.iview

import com.example.samplemvvm.utils.CommonViewState
import com.example.samplemvvm.utils.SingleLiveEvent
import com.example.samplemvvm.utils.SingleMediatorEvent

interface IViewModel<VS : IViewState> {
    val initialState: VS
    val viewState: SingleMediatorEvent<VS>
    var state: VS
    val commonViewState: SingleLiveEvent<CommonViewState>

    fun showLoading()

    fun hideLoading()
}
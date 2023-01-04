package com.example.samplemvvm.utils

import com.example.samplemvvm.iview.IViewState

sealed class CommonViewState: IViewState {
    object showLoading: CommonViewState()
    object hideLoading: CommonViewState()
}
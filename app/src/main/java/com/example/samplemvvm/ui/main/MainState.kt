package com.example.samplemvvm.ui.main

import com.example.samplemvvm.iview.IViewState

sealed class MainState: IViewState {
    object DefaultState: MainState()
    object ShowLoading: MainState()
    object HideLoading: MainState()

    data class LoginSuccess(val userName: String): MainState()
    data class LoginFail(val errorMessage: String): MainState()
}
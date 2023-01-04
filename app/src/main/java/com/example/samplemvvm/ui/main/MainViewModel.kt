package com.example.samplemvvm.ui.main

import com.example.samplemvvm.base.BaseViewModel
import com.example.samplemvvm.data.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository): BaseViewModel<MainState>() {
    override val initialState: MainState
        get() = MainState.DefaultState

    fun login(userName: String) {
        state = MainState.ShowLoading
        compositeDisposable.add(
            mainRepository.login(userName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    state = MainState.LoginSuccess(data)
                }, {
                    state = MainState.LoginFail(it.message.toString())
                })
        )
    }

}
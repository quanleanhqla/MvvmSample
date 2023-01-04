package com.example.samplemvvm.data.repository

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class MainRepository {
    fun login(userName: String): Single<String> {
        return Single.just("login")
    }
}
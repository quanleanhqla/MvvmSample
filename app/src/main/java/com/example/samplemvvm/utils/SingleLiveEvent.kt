package com.example.samplemvvm.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T>(value: T? = null) : MutableLiveData<T>(value) {
    private val pending = AtomicBoolean(false)
    private val observers = mutableSetOf<Observer<T>>()
    private val internalObserver = Observer<T> { t ->
        if (pending.compareAndSet(true, false)) {
            observers.forEach { observer -> observer.onChanged(t) }
        }
    }

    @Suppress("UNCHECKED_CAST")
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        observers.add(observer as Observer<T>)
        if (!hasObservers()) {
            super.observe(owner, internalObserver)
        }
    }

    override fun removeObservers(owner: LifecycleOwner) {
        observers.clear()
        super.removeObservers(owner)
    }

    @Suppress("UNCHECKED_CAST")
    override fun removeObserver(observer: Observer<in T>) {
        observers.remove(observer as Observer<T>)
        super.removeObserver(observer)
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }
}
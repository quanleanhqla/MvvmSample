package com.example.samplemvvm.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


@Suppress("UNCHECKED_CAST", "TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING", "UNUSED_PARAMETER")
class SingleMediatorEvent<T>(value: T? = null) : MediatorLiveData<T>() {
    private val pending = AtomicBoolean(false)
    private val observers = mutableSetOf<Observer<T>>()
    private val internalObserver = Observer<T> { t ->
        if (pending.compareAndSet(true, false)) {
            observers.forEach { observer -> observer.onChanged(t) }
        }
    }

    @MainThread
    override
    fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        observers.add(observer as Observer<T>)
        if (!hasObservers()) {
            super.observe(owner, internalObserver)
        }
    }

    override fun removeObservers(owner: LifecycleOwner) {
        observers.clear()
        super.removeObservers(owner)
    }

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
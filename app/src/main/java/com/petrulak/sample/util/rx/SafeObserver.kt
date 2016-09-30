package com.petrulak.sample.util.rx

import rx.Observer
import timber.log.Timber

open class SafeObserver<T> : Observer<T> {

  open fun onSafeNext(value: T) {
  }

  open fun onSafeError(e: Throwable) {
  }

  fun onSafeCompleted() {
  }

  override fun onNext(value: T) {
    try {
      Timber.d("Observer success %s", value)
      onSafeNext(value)
    } catch (e: Throwable) {
      Timber.e(e, "Observer onNext error")
    }
  }

  override fun onError(error: Throwable) {
    try {
      Timber.d(error, "Observer error")
      onSafeError(error)
    } catch (e: Throwable) {
      Timber.e(e, "Observer error")
    }
  }

  override fun onCompleted() {
    try {
      onSafeCompleted()
    } catch (e: Throwable) {
      Timber.e(e, "Observer onCompleted error")
    }
  }

}

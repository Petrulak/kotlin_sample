package com.petrulak.sample.ui.common

interface BaseView<T> {
  fun setPresenter(presenter: T)
}

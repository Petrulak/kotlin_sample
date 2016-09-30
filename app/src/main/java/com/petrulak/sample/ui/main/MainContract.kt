package com.petrulak.sample.ui.main

import com.petrulak.sample.domain.model.ModelData
import com.petrulak.sample.ui.common.BasePresenter
import com.petrulak.sample.ui.common.BaseView

import rx.Observable

class MainContract {

  interface View : BaseView<Presenter> {
    fun subscribeToNewGraphData(observableList: Observable<List<ModelData>>)

    fun onError()
  }

  interface Presenter : BasePresenter {
    fun getData(numberOfDays: String)

    fun getActualTimePeriod(): String
  }

}

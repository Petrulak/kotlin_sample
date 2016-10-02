package com.petrulak.sample.ui.main

import com.petrulak.sample.data.api.BlockchainAPI
import com.petrulak.sample.data.model.ApiResponse
import com.petrulak.sample.domain.mapper.ModelDataMapper
import com.petrulak.sample.domain.model.ModelData
import com.petrulak.sample.util.rx.SafeObserver
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject
import rx.subscriptions.CompositeSubscription
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: MainContract.View, private val blockchainAPI: BlockchainAPI) : MainContract.Presenter {
  private val subscriptions: CompositeSubscription
  private val dataEmitter: PublishSubject<List<ModelData>>
  private var actualPeriod: String

  init {
    actualPeriod = String()
    subscriptions = CompositeSubscription()
    dataEmitter = PublishSubject.create<List<ModelData>>()
    view.setPresenter(this)
  }

  override fun start() {
    view.subscribeToNewGraphData(dataEmitter.asObservable())
  }

  override fun getData(numberOfDays: String) {
    actualPeriod = numberOfDays
    blockchainAPI.getData(numberOfDays)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(object : SafeObserver<ApiResponse>() {
        override fun onSafeNext(response: ApiResponse) {
          handleResponse(response)
        }

        override fun onSafeError(e: Throwable) {
          Timber.e(e.toString())
          view.onError()
        }
      })
  }

  override fun getActualTimePeriod(): String {
    return actualPeriod
  }

  private fun handleResponse(input: ApiResponse) {
    val list = ModelDataMapper().mapToModel(input)
    emmitData(list)
  }

  private fun emmitData(modelData: List<ModelData>) {
    dataEmitter.onNext(modelData)
  }


}

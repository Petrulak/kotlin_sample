package com.petrulak.sample.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petrulak.sample.R
import com.petrulak.sample.domain.model.ModelData
import com.petrulak.sample.util.Constants
import com.petrulak.sample.util.extensions.isPortraitOrientation
import com.petrulak.sample.util.extensions.onError
import com.petrulak.sample.util.extensions.textAsString
import com.petrulak.sample.util.rx.SafeObserver
import kotlinx.android.synthetic.main.fragment_graph.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MainFragment : Fragment(), MainContract.View {

  private var presenter: MainContract.Presenter? = null
  private val subscriptions: CompositeSubscription
  private val adapter: MainAdapter
  private val layoutManager: GridLayoutManager

  init {
    subscriptions = CompositeSubscription()
    adapter = MainAdapter()
    layoutManager = GridLayoutManager(activity, Constants.NUM_GRID_PORTRAIT)
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater?.inflate(R.layout.fragment_graph, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initListeners()
    initRecyclerView()
  }

  private fun initListeners() {
    btn1.setOnClickListener { presenter?.getData(btn1.textAsString()) }
    btn2.setOnClickListener { presenter?.getData(btn2.textAsString()) }
    btn3.setOnClickListener { presenter?.getData(btn3.textAsString()) }
    btn4.setOnClickListener { presenter?.getData(btn4.textAsString()) }
  }

  private fun initRecyclerView() {
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = adapter
  }

  override fun onResume() {
    super.onResume()
    presenter?.start()
  }

  override fun onDestroy() {
    if (!subscriptions.isUnsubscribed) {
      subscriptions.unsubscribe()
    }
    super.onDestroy()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    if (savedInstanceState != null) {
      updateGridSize()
      presenter?.getData(savedInstanceState.getString(Constants.STATE_TEXT))
    }
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    if (presenter != null) {
      outState?.putString(Constants.STATE_TEXT, presenter?.getActualTimePeriod())
    }
  }

  override fun setPresenter(presenter: MainContract.Presenter) {
    this.presenter = presenter
  }

  override fun onError() {
    onError(R.string.error_msg_general)
  }

  override fun subscribeToNewGraphData(observableList: Observable<List<ModelData>>) {
    val subscription = observableList
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(object : SafeObserver<List<ModelData>>() {
        override fun onSafeNext(value: List<ModelData>) {
          updateRecyclerView(value)
        }
      })
    subscriptions.add(subscription)
  }

  private fun updateRecyclerView(list: List<ModelData>) {
    if (isAdded) {
      adapter.items = list
      recyclerView.scrollToPosition(0)
    }
  }

  private fun updateGridSize() {
    val gridSize = if (isPortraitOrientation()) Constants.NUM_GRID_PORTRAIT else Constants.NUM_GRID_LANDSCAPE
    layoutManager.spanCount = gridSize
  }

  companion object {
    fun newInstance(): MainFragment {
      return MainFragment()
    }
  }

}

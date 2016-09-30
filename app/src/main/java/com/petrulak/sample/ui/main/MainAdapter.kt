package com.petrulak.sample.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.petrulak.sample.R
import com.petrulak.sample.domain.model.ModelData
import com.petrulak.sample.util.extensions.notifyNewData
import com.petrulak.sample.util.extensions.inflate
import kotlinx.android.synthetic.main.item_rv_view.view.*
import kotlin.properties.Delegates


class MainAdapter() : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

  var items: List<ModelData> by Delegates.observable(emptyList()) {
    prop, old, new ->
    notifyNewData(old, new) { o, n -> o.date == n.date }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val v = parent.inflate(R.layout.item_rv_view, attachToRoot = false)
    return ViewHolder(v)
  }

  override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
    viewHolder!!.bindView(items[position])
  }

  override fun getItemCount(): Int {
    return items.size
  }

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindView(item: ModelData) {
      with(itemView) {
        tvDate.text = item.date
        tvPrice.text = "${item.value} $"
      }
    }
  }

}
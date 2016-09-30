package com.petrulak.sample.util.extensions

import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.petrulak.sample.util.Constants
import java.text.SimpleDateFormat
import java.util.*

interface Extensions

val graphDateFormat = SimpleDateFormat(Constants.GRAPH_X_AXIS_DATE_FORMAT)

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Button.textAsString(): String {
  return text.toString()
}

fun Long.toFormattedDateString(): String {
  return graphDateFormat.format(Date(this * 1000))
}

fun Fragment.onError(resID: Int) {
  if (isAdded) {
    Toast.makeText(context, getString(resID), Toast.LENGTH_LONG).show()
  }
}

fun <T> RecyclerView.Adapter<*>.notifyNewData(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {
  val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return compare(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
  })

  diff.dispatchUpdatesTo(this)
}


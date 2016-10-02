package com.petrulak.sample.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.petrulak.sample.MyApp
import com.petrulak.sample.R
import com.petrulak.sample.data.api.BlockchainAPI
import com.petrulak.sample.util.Constants
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject lateinit var blockchainAPI: BlockchainAPI
  var graphFragment: Fragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    MyApp.applicationGraph.inject(this)
    initFragment()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    supportFragmentManager.putFragment(outState, Constants.BASE_FRAG_TAG, graphFragment)
  }

  private fun initFragment() {

    graphFragment = supportFragmentManager.findFragmentByTag(Constants.BASE_FRAG_TAG)

    if (graphFragment == null) {
      graphFragment = MainFragment.newInstance()

      val transaction = supportFragmentManager.beginTransaction()
      transaction.add(R.id.flMain, graphFragment, Constants.BASE_FRAG_TAG)
      transaction.commit()
    }

    MainPresenter(graphFragment as MainFragment, blockchainAPI)

  }

}

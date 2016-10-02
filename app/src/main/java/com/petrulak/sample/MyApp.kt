package com.petrulak.sample

import android.app.Application
import com.facebook.stetho.Stetho
import com.petrulak.sample.di.AppComponent
import com.petrulak.sample.di.AppModule
import com.petrulak.sample.di.DaggerAppComponent
import com.petrulak.sample.di.NetModule
import timber.log.Timber

class MyApp : Application() {

  companion object {
    lateinit var applicationGraph: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    applicationGraph = DaggerAppComponent.builder()
      .appModule(AppModule(this))
      .netModule(NetModule())
      .build()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    Stetho.initializeWithDefaults(this)
  }

}
package com.petrulak.sample.di

import android.content.Context
import android.net.ConnectivityManager
import com.petrulak.sample.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val appContext: MyApp) {

  @Singleton
  @Provides
  internal fun provideAppContext(): Context {
    return appContext
  }

  @Singleton
  @Provides
  internal fun provideConnectivityManager(): ConnectivityManager {
    return appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  }

}

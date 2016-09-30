package com.petrulak.sample.di;

import android.content.Context;
import android.net.ConnectivityManager;

import com.petrulak.sample.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  private MyApp appContext;

  public AppModule(MyApp app) {
    this.appContext = app;
  }

  @Singleton
  @Provides
  Context provideAppContext() {
    return appContext;
  }

  @Singleton
  @Provides
  ConnectivityManager provideConnectivityManager() {
    return (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
  }


}

package com.petrulak.sample;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.petrulak.sample.di.AppComponent;
import com.petrulak.sample.di.AppModule;
import com.petrulak.sample.di.DaggerAppComponent;
import com.petrulak.sample.di.NetModule;

import timber.log.Timber;

public class MyApp extends Application {

  public AppComponent applicationGraph;

  @Override
  public void onCreate() {
    super.onCreate();
    applicationGraph = DaggerAppComponent.builder()
      .appModule(new AppModule(this))
      .netModule(new NetModule())
      .build();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    Stetho.initializeWithDefaults(this);
  }

}
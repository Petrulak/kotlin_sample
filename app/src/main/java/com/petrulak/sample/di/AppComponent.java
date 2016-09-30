package com.petrulak.sample.di;

import com.petrulak.sample.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
  void inject(MainActivity mainActivity);
}

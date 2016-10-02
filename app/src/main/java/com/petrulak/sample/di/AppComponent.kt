package com.petrulak.sample.di

import com.petrulak.sample.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {
  fun inject(mainActivity: MainActivity)
}

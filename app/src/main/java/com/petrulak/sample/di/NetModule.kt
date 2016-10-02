package com.petrulak.sample.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.petrulak.sample.BuildConfig
import com.petrulak.sample.data.api.BlockchainAPI
import com.petrulak.sample.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class NetModule {

  @Provides
  @Singleton
  internal fun provideRestAdapter(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(client).build()
  }

  @Provides
  @Singleton
  internal fun providePatientApi(retrofit: Retrofit): BlockchainAPI {
    return BlockchainAPI(retrofit)
  }


  @Provides
  @Singleton
  internal fun provideOkHttpClient(cache: Cache): OkHttpClient {

    val okHttpBuilder = OkHttpClient().newBuilder()
    okHttpBuilder.cache(cache)
    okHttpBuilder.addNetworkInterceptor(StethoInterceptor())
    return okHttpBuilder.build()
  }

  @Provides
  @Singleton
  internal fun provideCache(context: Context): Cache {
    val cacheDir = File(context.cacheDir, "http")
    if (!cacheDir.exists()) {
      cacheDir.mkdirs()
    }

    return Cache(cacheDir, Constants.DISK_CACHE_SIZE.toLong())
  }

}

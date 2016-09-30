package com.petrulak.sample.di;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.petrulak.sample.BuildConfig;
import com.petrulak.sample.data.api.BlockchainAPI;
import com.petrulak.sample.util.Constants;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

  @Provides
  @Singleton
  Retrofit provideRestAdapter(OkHttpClient client) {
    return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .client(client)
      .build();
  }

  @Provides
  @Singleton
  BlockchainAPI providePatientApi(Retrofit retrofit) {
    return new BlockchainAPI(retrofit);
  }


  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient(Cache cache) {

    final OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder();
    okHttpBuilder.cache(cache);
    okHttpBuilder.addNetworkInterceptor(new StethoInterceptor());
    return okHttpBuilder.build();
  }

  @Provides
  @Singleton
  Cache provideCache(Context context) {
    File cacheDir = new File(context.getCacheDir(), "http");
    if (!cacheDir.exists()) {
      cacheDir.mkdirs();
    }

    return new Cache(cacheDir, Constants.INSTANCE.getDISK_CACHE_SIZE());
  }

}

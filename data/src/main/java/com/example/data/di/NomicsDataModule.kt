package com.example.data.di

import android.content.Context
import com.example.data.api.NomicsApi
import com.example.data.db.NomicsDb
import com.example.data.repository.NomicRepository
import com.example.data.util.NomicsInterceptor
import com.example.domain.repository.PricesRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NomicsDataModule {

    private const val nomics_base_url = "https://api.nomics.com"

    @JvmStatic
    @Provides
    fun provideNomicsIntercepter() : NomicsInterceptor = NomicsInterceptor()

    @JvmStatic
    @Provides
    fun provideNomicsHttpClient(nomicsInterceptor: NomicsInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(nomicsInterceptor)
                .build()

    @JvmStatic
    @Provides
    fun provideMoshiConverterFactory() : MoshiConverterFactory = MoshiConverterFactory.create()

    @JvmStatic
    @Provides
    fun provideNomicsRetrofit(moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient) : Retrofit =
            Retrofit.Builder()
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .baseUrl(nomics_base_url)
                .build()

    @JvmStatic
    @Provides
    fun provideNomicsApi(retrofit: Retrofit) : NomicsApi =
            retrofit.create(NomicsApi::class.java)

    @JvmStatic
    @Provides
    fun provideNomicsDb(context: Context) : NomicsDb =
            NomicsDb(context)

    @JvmStatic
    @Provides
    fun bindsNomicsRepo(nomicsApi: NomicsApi,
                        nomicsDb: NomicsDb) : PricesRepository {
        return NomicRepository(nomicsApi, nomicsDb)
    }
}
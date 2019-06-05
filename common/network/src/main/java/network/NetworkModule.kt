package network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule(private val apiBase: String,
                    private val interceptor: Interceptor? = null) {


    @Reusable
    @Provides
    fun providesMoshiConverter() : MoshiConverterFactory =
            MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun providesOkHttpClient() : OkHttpClient {
        val builder = OkHttpClient.Builder()
        interceptor?.let {
            builder.addInterceptor(it)
        }
        return builder.build()
    }

    @Reusable
    @Provides
    fun providesRetrofitClient(moshiConverterFactory: MoshiConverterFactory,
                               okHttpClient: OkHttpClient) : Retrofit =
            Retrofit.Builder()
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .baseUrl(apiBase)
                .build()
}
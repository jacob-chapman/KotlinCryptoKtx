package network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\nH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lnetwork/NetworkModule;", "", "apiBase", "", "interceptor", "Lokhttp3/Interceptor;", "(Ljava/lang/String;Lokhttp3/Interceptor;)V", "providesMoshiConverter", "Lretrofit2/converter/moshi/MoshiConverterFactory;", "providesOkHttpClient", "Lokhttp3/OkHttpClient;", "providesRetrofitClient", "Lretrofit2/Retrofit;", "moshiConverterFactory", "okHttpClient", "network"})
@dagger.Module()
public abstract class NetworkModule {
    private final java.lang.String apiBase = null;
    private final okhttp3.Interceptor interceptor = null;
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @dagger.Reusable()
    public final retrofit2.converter.moshi.MoshiConverterFactory providesMoshiConverter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final okhttp3.OkHttpClient providesOkHttpClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @dagger.Reusable()
    public final retrofit2.Retrofit providesRetrofitClient(@org.jetbrains.annotations.NotNull()
    retrofit2.converter.moshi.MoshiConverterFactory moshiConverterFactory, @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    public NetworkModule(@org.jetbrains.annotations.NotNull()
    java.lang.String apiBase, @org.jetbrains.annotations.Nullable()
    okhttp3.Interceptor interceptor) {
        super();
    }
}
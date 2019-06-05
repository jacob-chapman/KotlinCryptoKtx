package network;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetworkModule_ProvidesRetrofitClientFactory implements Factory<Retrofit> {
  private final NetworkModule module;

  private final Provider<MoshiConverterFactory> moshiConverterFactoryProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public NetworkModule_ProvidesRetrofitClientFactory(
      NetworkModule module,
      Provider<MoshiConverterFactory> moshiConverterFactoryProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    this.module = module;
    this.moshiConverterFactoryProvider = moshiConverterFactoryProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public Retrofit get() {
    return providesRetrofitClient(
        module, moshiConverterFactoryProvider.get(), okHttpClientProvider.get());
  }

  public static NetworkModule_ProvidesRetrofitClientFactory create(
      NetworkModule module,
      Provider<MoshiConverterFactory> moshiConverterFactoryProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    return new NetworkModule_ProvidesRetrofitClientFactory(
        module, moshiConverterFactoryProvider, okHttpClientProvider);
  }

  public static Retrofit providesRetrofitClient(
      NetworkModule instance,
      MoshiConverterFactory moshiConverterFactory,
      OkHttpClient okHttpClient) {
    return Preconditions.checkNotNull(
        instance.providesRetrofitClient(moshiConverterFactory, okHttpClient),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

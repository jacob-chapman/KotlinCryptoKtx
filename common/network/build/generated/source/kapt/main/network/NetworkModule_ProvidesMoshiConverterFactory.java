package network;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetworkModule_ProvidesMoshiConverterFactory
    implements Factory<MoshiConverterFactory> {
  private final NetworkModule module;

  public NetworkModule_ProvidesMoshiConverterFactory(NetworkModule module) {
    this.module = module;
  }

  @Override
  public MoshiConverterFactory get() {
    return providesMoshiConverter(module);
  }

  public static NetworkModule_ProvidesMoshiConverterFactory create(NetworkModule module) {
    return new NetworkModule_ProvidesMoshiConverterFactory(module);
  }

  public static MoshiConverterFactory providesMoshiConverter(NetworkModule instance) {
    return Preconditions.checkNotNull(
        instance.providesMoshiConverter(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

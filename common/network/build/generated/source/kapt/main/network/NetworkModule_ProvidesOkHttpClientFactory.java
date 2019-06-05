package network;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetworkModule_ProvidesOkHttpClientFactory implements Factory<OkHttpClient> {
  private final NetworkModule module;

  public NetworkModule_ProvidesOkHttpClientFactory(NetworkModule module) {
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return providesOkHttpClient(module);
  }

  public static NetworkModule_ProvidesOkHttpClientFactory create(NetworkModule module) {
    return new NetworkModule_ProvidesOkHttpClientFactory(module);
  }

  public static OkHttpClient providesOkHttpClient(NetworkModule instance) {
    return Preconditions.checkNotNull(
        instance.providesOkHttpClient(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

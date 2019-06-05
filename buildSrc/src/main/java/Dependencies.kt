
object Modules {
    val app = ":app"
    val news = ":news"

    val nomics = ":nomics"
    val domain = ":domain"
    val newsFeature = ":newsfeature"

    val common_core = ":core"
    val common_network = ":network"
    val common_android = ":android"

    val buildSrc = ":buildSrc"
}

object Application {
    val sdkVersion = 28
    val appId = "com.example.kotlincrypto_ktx"
    val minSdkVersion = 23
}

object Release {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val kotlin = "1.3.31"
    val kotlinCoroutine = "1.1.1"
    val dagger = "2.22.1"
    val room = "2.1.0-rc01"
    val retrofit = "2.5.0"
    val retrofitCoroutines = "0.9.2"
    val moshi = "1.8.0"
    val livedata = "2.2.0-alpha01"
    val androidKtx = "1.0.2"
    val appCompat = "1.0.2"
    val constraintLayout = "2.0.0-beta1"
}

object TestVersions {
    val junit = "4.12"
    val androidJunit = "1.1.0"
    val espresso = "3.1.1"
}

object Libraries {
    val kotlin =  "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val androidKtx = "androidx.core:core-ktx:${Versions.androidKtx}"

    val liveDataCore = "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.livedata}"
    val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata}"

    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutine}"
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutine}"

    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object SupportLibraries {
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
}

object TestLibraries {
    val junit = "junit:junit:${TestVersions.junit}"
    val androidJunit = "androidx.test.ext:junit:${TestVersions.androidJunit}"
    val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"
}
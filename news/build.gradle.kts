
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Application.sdkVersion)
    defaultConfig{
        minSdkVersion(Application.minSdkVersion)
        targetSdkVersion(Application.sdkVersion)
        versionCode = Release.versionCode
        versionName = Release.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.prog")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(project(Modules.domain))

    implementation(Libraries.kotlin)
    implementation(SupportLibraries.appCompat)

    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.androidJunit)
    androidTestImplementation(TestLibraries.espresso)

    //Room
    api(Libraries.roomRuntime)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.roomKtx)

    //Dagger
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)

    //Networking
    implementation(Libraries.retrofit)
    api(Libraries.moshiConverter)
    api(Libraries.moshi)
    kapt(Libraries.moshiKotlinCodegen)
    api(Libraries.moshiKotlin)
    implementation(Libraries.retrofitCoroutines)

    //Android LiveData
    implementation(Libraries.liveDataKtx)
    implementation(Libraries.liveDataCore)

    //Kotlin Coroutines
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)


}
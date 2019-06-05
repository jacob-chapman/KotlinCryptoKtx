plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}


android{
    compileSdkVersion(Application.sdkVersion)

    defaultConfig{
        minSdkVersion(Application.minSdkVersion)
        targetSdkVersion(Application.sdkVersion)
        versionCode = Release.versionCode
        versionName = Release.versionName
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.kotlin)
    implementation(Libraries.androidKtx)

    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.androidJunit)
    androidTestImplementation(TestLibraries.espresso)

    implementation(Libraries.dagger)

    implementation(Libraries.liveDataKtx)
    implementation(Libraries.liveDataCore)
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.livedata}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.livedata}")
    implementation("androidx.lifecycle:lifecycle-common:${Versions.livedata}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.livedata}")

    implementation(Libraries.coroutinesAndroid)
    implementation(Libraries.coroutinesCore)

    implementation("androidx.activity:activity-ktx:1.0.0-alpha08")
    implementation("androidx.fragment:fragment-ktx:1.1.0-alpha09")
    implementation("androidx.navigation:navigation-fragment-ktx:2.0.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.0.0")
}
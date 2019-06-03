
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Application.sdkVersion)
    defaultConfig{
        applicationId = Application.appId
        minSdkVersion(Application.minSdkVersion)
        targetSdkVersion(Application.sdkVersion)
        versionCode = Release.versionCode
        versionName = Release.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.prog")
        }
    }

    dataBinding{
        isEnabled = true
    }

    dynamicFeatures = mutableSetOf(Modules.newsFeature)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.nomics))
    implementation(project(Modules.domain))
    implementation(project(Modules.news))

    implementation(Libraries.kotlin)
    implementation(SupportLibraries.constraintLayout)

    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.androidJunit)
    androidTestImplementation(TestLibraries.espresso)

    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)

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

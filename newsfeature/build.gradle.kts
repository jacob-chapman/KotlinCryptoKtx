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

    implementation(project(Modules.common_core))
    implementation(project(Modules.common_network))

    implementation(Libraries.kotlin)
    implementation(Libraries.androidKtx)

    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)

    implementation(Libraries.liveDataKtx)
    implementation(Libraries.liveDataCore)
    implementation(SupportLibraries.constraintLayout)
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.livedata}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.livedata}")
    implementation("androidx.lifecycle:lifecycle-common:${Versions.livedata}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.livedata}")

    implementation("androidx.activity:activity-ktx:1.0.0-alpha08")
    implementation("androidx.fragment:fragment-ktx:1.1.0-alpha09")
    implementation("androidx.navigation:navigation-fragment-ktx:2.0.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.0.0")
}
repositories {
    mavenCentral()
}

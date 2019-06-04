plugins {
    id("com.android.dynamic-feature")
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
    implementation(project(Modules.app))
    implementation(project(Modules.common_core))

    implementation(Libraries.kotlin)
    implementation("androidx.core:core-ktx:+")
}
repositories {
    mavenCentral()
}

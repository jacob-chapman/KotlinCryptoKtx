
plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.kotlin)

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

}

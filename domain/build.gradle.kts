plugins{
    id("java")
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.kotlin)

    //Dagger
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)


    //Kotlin Coroutines
    implementation(Libraries.coroutinesCore)

    testImplementation(TestLibraries.junit)

}

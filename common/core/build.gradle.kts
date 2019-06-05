plugins {
    `java-library`
    id("kotlin")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.kotlin)
    implementation(Libraries.retrofit)

    //Kotlin Coroutines
    implementation(Libraries.coroutinesCore)
}

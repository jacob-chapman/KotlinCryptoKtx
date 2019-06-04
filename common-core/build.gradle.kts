plugins {
    id("java-library")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.retrofit)
}

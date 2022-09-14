plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.cdcoding.movienight"
        minSdk = 21
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    val daggerVersion: String by rootProject.extra

    // Dagger
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerVersion")

    implementation(project(":login"))
}
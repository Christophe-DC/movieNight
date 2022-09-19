import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }
    buildTypes {
        release {
            val apiKey = gradleLocalProperties(rootDir).getProperty("RELEASE_API_KEY")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
        }
        debug {
            val apiKey = gradleLocalProperties(rootDir).getProperty("DEBUG_API_KEY")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
        }
    }
}

dependencies {
    val retrofitVersion: String by rootProject.extra
    val daggerVersion: String by rootProject.extra
    val hiltVersion: String by rootProject.extra

    // Retrofit
    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // Dagger
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerVersion")

    // Hilt
    kapt("androidx.hilt:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltVersion")

    implementation(project(":database"))
}

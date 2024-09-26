plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }
}

dependencies {

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

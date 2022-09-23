plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}


android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeVersion: String by rootProject.extra
    val daggerVersion: String by rootProject.extra
    val hiltVersion: String by rootProject.extra
    val workVersion: String by rootProject.extra
    val lifecycleVersion: String by rootProject.extra


    //Compose
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("io.coil-kt:coil-compose:2.2.1")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.24.7-alpha")
    implementation("com.google.accompanist:accompanist-coil:0.12.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.16.1")

    // Dagger
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerVersion")

    // Hilt
    kapt("androidx.hilt:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltVersion")
    implementation("androidx.hilt:hilt-work:$hiltVersion")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // ExoPlayer
    implementation("com.google.android.exoplayer:exoplayer:2.18.1")

    // Youtube extractor
    implementation("com.github.HaarigerHarald:android-youtubeExtractor:master-SNAPSHOT")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")


    implementation(project(":database"))
    implementation(project(":common"))
}
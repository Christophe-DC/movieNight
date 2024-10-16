plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cdcoding.movienightapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        multiDexEnabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }


    signingConfigs {
        create("appstore") {
            storeFile = file("../appstore.jks")
            storePassword = "password"
            keyAlias = "key0"
            keyPassword = "password"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("appstore")
            isMinifyEnabled = false
            isDebuggable = true
        }
        release {
            signingConfig = signingConfigs.getByName("appstore")
            isDebuggable = false
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    lintOptions {
        baseline(file("lint-baseline.xml"))
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
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

    //Compose
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.24.7-alpha")
    implementation("com.google.accompanist:accompanist-coil:0.12.0")

    // Dagger
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerVersion")

    // Hilt
    kapt("androidx.hilt:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltVersion")
    implementation("androidx.hilt:hilt-work:$hiltVersion")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(project(":login"))
    implementation(project(":movies"))
    implementation(project(":movieDetail"))
    implementation(project(":common"))


    implementation("com.android.support:multidex:2.0.1")
}
val composeVersion by extra { "1.3.1" }
val daggerVersion by extra { "2.44" }
val hiltVersion by extra { "1.1.0" }
val coroutineVersion by extra { "1.6.4" }
val lifecycleVersion by extra { "2.5.1" }
val retrofitVersion by extra { "2.9.0" }
val workVersion by extra { "2.8.0" }

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}
val composeVersion by extra { "1.2.1" }
val daggerVersion by extra { "2.42" }
val hiltVersion by extra { "1.0.0" }
val coroutineVersion by extra { "1.6.4" }
val lifecycleVersion by extra { "2.5.1" }
val retrofitVersion by extra { "2.9.0" }
val workVersion by extra { "2.7.1" }

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
    }
}
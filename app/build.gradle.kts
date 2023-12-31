plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
   // id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.teamkuy2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.teamkuy2"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.github.bumptech.glide:glide:4.15.0")

    //recyclerview
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.loopj.android:android-async-http:1.4.9")

    //retrofit
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

//    ksp("androidx.room:room-compiler:2.5.2")

    //firebase
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation(platform("com.google.firebase:firebase-bom:32.4.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    //datastore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    //untuk api github
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0-alpha03")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0-alpha03")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0-alpha03")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0-alpha03")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0-alpha03")
    
    kapt ("androidx.lifecycle:lifecycle-compiler:2.7.0-alpha03")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.7.0-alpha03")
    implementation("io.coil-kt:coil:1.4.0")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("androidx.activity:activity-ktx:1.8.0")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation ("androidx.room:room-runtime:2.4.2")
    kapt ("androidx.room:room-compiler:2.4.2")
    implementation ("androidx.room:room-rxjava3:2.4.2")

}
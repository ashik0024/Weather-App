plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialize)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.hilt.android)


}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources=false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources=true
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
    buildFeatures{
        viewBinding=true
        dataBinding=true
        compose=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compose.version.get()

    }

dependencies {


    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    with (libs) {

        // Kotlin
        implementation(kotlin.stdlib)
        implementation(core.ktx)
        implementation(kotlin.coroutines)
        implementation(kotlin.json.serialization)

        // Hilt
        implementation(bundles.hilt)
        ksp(hilt.compiler)
        ksp(hilt.compiler.kapt)
        ksp(hilt.android.compiler)

        // Jetpack
        ksp(room.kapt)
        implementation(paging)
        implementation(bundles.room)

        implementation(bundles.compose)
        implementation(work.manager.ktx)
        implementation(bundles.lifecycle)
        implementation(bundles.navigation)

        // Image
        implementation(bundles.coil)

        // Network
        implementation(bundles.retrofit)

        // location
        implementation(loaction.service)


    }
}
}
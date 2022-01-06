plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    compileSdk = Apps.compileSdk
    buildToolsVersion = Apps.buildTools

    buildFeatures {
        compose = true
    }

    defaultConfig {
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.androidXCore)
    implementation(Libs.androidXCompat)
    implementation(Libs.coroutines)
    implementation(Libs.gson)
    implementation(Libs.androidXActivityKotlin)

    implementation(Libs.orbitMVI)

    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)

    implementation(Libs.worker)

    testImplementation(UnitTestLibs.unitTestJUnit)
    testImplementation(UnitTestLibs.mockitoCore)
    testImplementation(UnitTestLibs.mockitoKotlin)
    testImplementation(UnitTestLibs.truth)
    testImplementation(UnitTestLibs.kotlinCoroutine)
    testImplementation(UnitTestLibs.androidXArchCoreTesting)

    androidTestImplementation(UiTestLibs.androidTestJUnit)
    androidTestImplementation(UiTestLibs.androidTestEspresso)

    // Integration with activities
    implementation(Libs.composeActivity)
    // Compose Material Design
    implementation(Libs.composeMaterial)
    // Animations
    implementation(Libs.composeAnimation)
    // Tooling support (Previews, etc.)
    implementation(Libs.composeTooling)
    // Integration with ViewModels
    implementation(Libs.composeViewModel)

    implementation(Libs.composeNavigation)
    implementation(Libs.composeHiltNavigation)
    // UI Tests
    androidTestImplementation(Libs.composeTesting)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

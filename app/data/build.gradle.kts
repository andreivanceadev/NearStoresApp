plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Apps.compileSdk
    buildToolsVersion = Apps.buildTools

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
}


hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.androidXCore)
    implementation(Libs.androidXCompat)
    implementation(Libs.coroutines)
    implementation(Libs.gson)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitGsonConverter)
    implementation(Libs.okHttp)
    implementation(Libs.loggingInterceptor)

    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)

    testImplementation(UnitTestLibs.unitTestJUnit)
    testImplementation(UnitTestLibs.mockitoCore)
    testImplementation(UnitTestLibs.mockitoKotlin)
    testImplementation(UnitTestLibs.truth)
    testImplementation(UnitTestLibs.kotlinCoroutine)
    testImplementation(UnitTestLibs.androidXArchCoreTesting)

    androidTestImplementation(UiTestLibs.androidTestJUnit)
    androidTestImplementation(UiTestLibs.androidTestEspresso)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

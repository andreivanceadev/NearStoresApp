plugins {
    id("com.android.application")
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

    val versionFromGradleProperty = project.property("VERSION_NAME") as String

    defaultConfig {
        applicationId = "com.andreivanceadev.NearStoresApp"
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode(versionFromGradleProperty)
        versionName = versionFromGradleProperty
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
    packagingOptions {
        resources.excludes.add("META-INF/*")
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


hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(project(":app:features:stores"))

    implementation(Libs.kotlin)
    implementation(Libs.androidXCore)
    implementation(Libs.material)
    implementation(Libs.androidXCompat)

    implementation(Libs.orbitMVI)
    implementation(Libs.orbitMVICore)

    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)

    testImplementation(UnitTestLibs.unitTestJUnit)
    testImplementation(UnitTestLibs.mockitoCore)
    testImplementation(UnitTestLibs.mockitoKotlin)
    testImplementation(UnitTestLibs.truth)

    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutines)

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
}

kapt {
    correctErrorTypes = true
}
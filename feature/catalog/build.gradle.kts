plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.belkanoid.catalog"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(Libs.CORE)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.MATERIAL)

    implementation(Libs.VIEW_BINDING_DELEGATE)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.ADAPTER_DELEGATES)
    implementation(Libs.ADAPTER_DELEGATES_VIEW_BINDING)
    implementation(Libs.COROUTINES)
    implementation(Libs.COROUTINES_LIFECYCLE)
    implementation(Libs.DAGGER)
    ksp(Libs.DAGGER_COMPILER)

    implementation(project(Modules.di))
    implementation(project(Modules.core))

    implementation(project(Modules.Navigation.navigationApi))

    implementation(project(Modules.Domain.product))
    implementation(project(Modules.Domain.productRemote))
}
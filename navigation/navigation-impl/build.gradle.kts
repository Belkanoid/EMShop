plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.belkanoid.navigation_impl"
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

    implementation(Libs.DAGGER)
    ksp(Libs.DAGGER_COMPILER)

    implementation(Libs.NAVIGATION_FRAGMENT)
    implementation(Libs.NAVIGATION_UI)
    implementation(Libs.VIEW_BINDING_DELEGATE)

    implementation(project(Modules.core))
    implementation(project(Modules.di))

    implementation(project(Modules.Feature.login))
    implementation(project(Modules.Feature.home))
    implementation(project(Modules.Feature.profile))
    implementation(project(Modules.Feature.catalog))
    implementation(project(Modules.Feature.discount))
    implementation(project(Modules.Feature.cart))
    
    implementation(project(Modules.Navigation.navigationApi))


    implementation(project(Modules.Domain.product))
}
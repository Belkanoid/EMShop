plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.belkanoid.effectivemobileshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.belkanoid.effectivemobileshop"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {
    implementation(Libs.CORE)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.MATERIAL)

    implementation(Libs.COROUTINES)
    implementation(Libs.COROUTINES_LIFECYCLE)



    implementation(Libs.DAGGER)
    ksp(Libs.DAGGER_COMPILER)
    implementation(Libs.NAVIGATION_FRAGMENT)
    implementation(Libs.NAVIGATION_UI)


    implementation(project(Modules.Navigation.navigationApi))
    implementation(project(Modules.Navigation.navigationImpl))
    implementation(project(Modules.core))
    implementation(project(Modules.di))

    implementation(project(Modules.Feature.login))
    implementation(project(Modules.Feature.home))
    implementation(project(Modules.Feature.profile))
    implementation(project(Modules.Feature.catalog))

    implementation(project(Modules.Data.remote))
    implementation(project(Modules.Data.local))

    implementation(project(Modules.Domain.productRemote))
    implementation(project(Modules.Domain.product))

}
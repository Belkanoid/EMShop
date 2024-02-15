pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "Effective Mobile Shop"
include(":app")
include(":navigation:navigation-api")
include(":navigation:navigation-impl")
include(":di")
include(":feature:log-in")
include(":feature:home")
include(":feature:catalog")
include(":feature:cart")
include(":feature:discount")
include(":feature:profile")
include(":core")
include(":data:remote")
include(":domain:product")
include(":domain:product-remote")
include(":data:local")

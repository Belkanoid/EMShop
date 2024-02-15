object Modules {

    object Navigation {
        const val navigationApi = ":navigation:navigation-api"
        const val navigationImpl = ":navigation:navigation-impl"
    }


    const val di = ":di"
    const val core = ":core"

    object Data {
        const val remote = ":data:remote"
        const val local = ":data:local"
    }

    object Domain {
        const val product = ":domain:product"


        const val productRemote = ":domain:product-remote"


    }

    object Feature {
        const val login = ":feature:log-in"

        const val home = ":feature:home"
        const val profile = ":feature:profile"
        const val catalog = ":feature:catalog"
        const val discount = ":feature:discount"
        const val cart = ":feature:cart"
    }
}
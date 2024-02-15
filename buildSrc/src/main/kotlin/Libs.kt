object Libs {
    //Dependencies
    private const val navigationVersion = "2.7.6"
    private const val daggerVersion = "2.50"
    private const val coroutinesVersion = "1.6.4"
    private const val coroutinesLifecycleVersion = "2.6.2"


    //Android
    const val CORE = "androidx.core:core-ktx:1.12.0"
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.6.1"
    const val MATERIAL = "com.google.android.material:material:1.11.0"


    //Coroutines
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    const val COROUTINES_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$coroutinesLifecycleVersion"
    const val COROUTINES_LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:$coroutinesLifecycleVersion"


    //Dagger2
    const val DAGGER = "com.google.dagger:dagger:$daggerVersion"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:$daggerVersion"


    //Fragment-KTX
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.6.2"


    //Navigation
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$navigationVersion"


    //ViewGroup
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"


    //ViewBindingDelegate
    const val VIEW_BINDING_DELEGATE = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.9"


    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val RETROFIT_CONVERTOR = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val VIEW_PAGER2 = "androidx.viewpager2:viewpager2:1.0.0"

    const val ADAPTER_DELEGATES = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:4.3.2"
    const val ADAPTER_DELEGATES_VIEW_BINDING = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.2"
}
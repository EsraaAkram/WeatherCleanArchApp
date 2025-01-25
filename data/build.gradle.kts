import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.esoapps.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24


        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())


        //TEST DURING CODING:
        val apiKey = properties.getProperty("API_KEY") ?: ""
        val apiUrl = properties.getProperty("API_URL") ?: ""
        println(apiKey)
        println(apiUrl)

        buildConfigField("String", "API_KEY", "\"${properties["API_KEY"]}\"")
        buildConfigField("String", "API_URL", "\"${properties["API_URL"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false

        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gsonconverter)
    implementation(libs.okhHttp.core)
    implementation(libs.okhHttp.interceptor)
    implementation(libs.gson)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)
    implementation(libs.hilt.android)
    ksp(libs.hilt.kapt)
    implementation(libs.hilt.navigation.compose)

}
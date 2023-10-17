import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
}

val localProperties = gradleLocalProperties(rootDir)
android {
    namespace = "ru.mishenko.maksim.schedule"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.mishenko.maksim.schedule"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        manifestPlaceholders["appAuthRedirectScheme"] = "ru.mishenko.maksim.shedule.oauth"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            debug {
                buildConfigField("String", "leaderIdUrl", localProperties.getProperty("leaderIdUrl"))
                buildConfigField("String", "leaderApiKey", localProperties.getProperty("leaderApiKey"))
                buildConfigField("String", "leaderIdSecret", localProperties.getProperty("leaderIdSecret"))
                buildConfigField("String", "omgtuScheduleUrl", localProperties.getProperty("omgtuScheduleUrl"))
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            resources.excludes.add("META-INF/*")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // ktor
    implementation("io.ktor:ktor-client-core:2.3.4")
    implementation("io.ktor:ktor-client-cio:2.3.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4") //serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation("io.ktor:ktor-client-logging:2.3.4") //logger

    // WebView
    implementation("com.google.accompanist:accompanist-webview:0.33.1-alpha")

    // koin
    implementation("io.insert-koin:koin-core:3.4.3")
    implementation("io.insert-koin:koin-android:3.4.3")

    //decompose
    implementation("com.arkivanov.decompose:decompose:2.0.2")
    implementation("com.arkivanov.decompose:extensions-compose-jetpack:2.0.2")
    implementation("com.arkivanov.decompose:extensions-android:2.0.2")

    //google calendar
    implementation("com.google.auth:google-auth-library-oauth2-http:1.3.0")
    implementation("com.google.apis:google-api-services-calendar:v3-rev20211026-1.32.1")

}
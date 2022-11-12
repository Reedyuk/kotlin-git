import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version "1.7.20"
    id("com.android.library")
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    `maven-publish`
//    id("signing")
}

group = "uk.co.andrewreed"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

val frameworkName = "git"
val kotlinx_coroutines_version: String by project

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    android {
        publishAllLibraryVariants()
    }
    val xcf = XCFramework(frameworkName)
    iosArm64("ios") {
        binaries.framework {
            baseName = frameworkName
            xcf.add(this)
        }
    }
    iosX64 {
        binaries.framework {
            baseName = frameworkName
            xcf.add(this)
        }
    }
    iosSimulatorArm64 {
        binaries.framework {
            baseName = frameworkName
            xcf.add(this)
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val androidMain by getting
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val iosX64Main by getting
        iosX64Main.dependsOn(iosMain)

        val iosSimulatorArm64Test by getting
        iosSimulatorArm64Test.dependsOn(iosTest)
    }
}

ktlint {
    version.set("0.43.0")
}

android {
    compileSdk = 31 // we have to set to 31 due to multiplatform settings dependancy having this at 31
    buildToolsVersion = "30.0.3"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

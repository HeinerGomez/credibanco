plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.avility.presentation"
}

dependencies {
    implementation(project(Modules.shared))
    implementation(project(Modules.domain))

    implementation(Coil.coilCompose)
}
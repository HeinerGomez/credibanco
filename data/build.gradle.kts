plugins {
    `android-library`
    `kotlin-android`
    `kotlin-kapt`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.avility.data"
}

dependencies {
    implementation(project(Modules.shared))
    implementation(project(Modules.domain))

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    kapt(RoomDB.roomCompiler)
    implementation(RoomDB.roomRuntime)
    implementation(RoomDB.roomKtx)

}
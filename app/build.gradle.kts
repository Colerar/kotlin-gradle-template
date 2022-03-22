import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // apply the shadow plugin to add support for build jar including dependencies
    id("com.github.johnrengelman.shadow")

    // code linter
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jlleitschuh.gradle.ktlint-idea")
}

group = "example"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation(Kotlin.Stdlib.jdk8)

    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.jdk9)

    // uncomment or delete them on demand, then run gradle task `refreshVersions`
    // implementation(KotlinX.datetime)
    // implementation(Kotlin.module("kotlin-reflect"))

    // test frameworks
    testImplementation(Kotlin.Test.annotationsCommon)
    testImplementation(Kotlin.Test.common)
    testImplementation(Kotlin.Test.junit5)

    testImplementation(KotlinX.coroutines.test)
}

application {
    // Define the main class for the application.
    mainClass.set("$group.AppKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.apply {
        jvmTarget = "17"
        // freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
}

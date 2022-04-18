import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.6.20"
    application
}

group = "de.carina"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation (platform("org.lwjgl:lwjgl-bom:3.2.3"))

    implementation ("org.lwjgl:lwjgl")
    implementation ("org.lwjgl:lwjgl-assimp")
    implementation ("org.lwjgl:lwjgl-glfw")
    implementation ("org.lwjgl:lwjgl-opengl")
    implementation ("org.lwjgl:lwjgl-stb")

    implementation ("org.joml:joml:1.10.4")

    runtimeOnly("org.lwjgl:lwjgl::natives-windows")
    runtimeOnly ("org.lwjgl:lwjgl-assimp::natives-windows")
    runtimeOnly ("org.lwjgl:lwjgl-glfw::natives-windows")
    runtimeOnly ("org.lwjgl:lwjgl-opengl::natives-windows")
    runtimeOnly ("org.lwjgl:lwjgl-stb::natives-windows")

    implementation("org.jetbrains:annotations:23.0.0")
    testImplementation(kotlin("test"))
    compileOnly("org.projectlombok:lombok:1.18.22")
    testImplementation("org.projectlombok:lombok:1.18.22")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")
    implementation ("org.xerial:sqlite-jdbc:3.36.0.3")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    test {
        useJUnitPlatform()
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
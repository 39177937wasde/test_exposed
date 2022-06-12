import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val exposedVersion: String by project
plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.owner"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("com.h2database:h2:2.1.212")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.slf4j:slf4j-log4j12:1.7.32")
    implementation("mysql:mysql-connector-java:8.0.28")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
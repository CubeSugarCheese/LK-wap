import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.serialization") version "1.7.0"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.daniel"
version = "0.0.1"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val ktor_version: String by project
val ktorm_version: String by project
val gson_version: String by project
val hocon_mapper_version: String by project
dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.9.0")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cio-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    // ktor-server
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.19.0")
    // ktor-client
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    // ktorm
    implementation("org.ktorm:ktorm-core:$ktorm_version")
    implementation("org.ktorm:ktorm-support-sqlite:$ktorm_version")
    // 杂项
    implementation("com.github.houbb:opencc4j:1.7.2")
    implementation("org.jsoup:jsoup:1.15.3")
    implementation("me.tongfei:progressbar:0.9.4")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.create("buildAndCopy") {
    dependsOn("build")
    copy {
        from("build/libs/${project.name}-$version-all.jar")
        into("../LK-wap-deploy")
    }
}

application {
    mainClass.set("MainKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

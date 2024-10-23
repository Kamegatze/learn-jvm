plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.flywaydb.flyway") version "10.15.2"
}

group = "com.kamegatze"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val mapstruct = "1.6.2"
val flexmark = "0.64.0"
val jsoup = "1.18.1"
val mapstructSpringAnnotation = "0.1.2"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.mapstruct.extensions.spring:mapstruct-spring-annotations:$mapstructSpringAnnotation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("com.vladsch.flexmark:flexmark-all:$flexmark")
    implementation("org.jsoup:jsoup:$jsoup")
    implementation("org.mapstruct:mapstruct:$mapstruct")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstruct")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.10.0")
    }
}

flyway{
    url = "jdbc:postgresql://localhost:5432/learn-jvm"
    user = "postgres"
    password = "postgres"
}

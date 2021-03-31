plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.31"
    id("org.jetbrains.kotlin.kapt") version "1.4.31"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.31"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.4.31"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("io.micronaut.application") version "1.4.2"
}

version = "0.3"
group = "com.casadodev"

val kotlinVersion= project.properties["kotlinVersion"]
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.casadodev.*")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")

    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.4.10")

    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-http-client")
    implementation("javax.annotation:javax.annotation-api")
    implementation("io.micronaut:micronaut-validation")

    implementation("io.micronaut.sql:micronaut-hibernate-jpa")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("org.postgresql:postgresql")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")

    // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-bom
//    implementation("com.amazonaws:aws-java-sdk-bom:1.11.988")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/aws-sdk-java
//    implementation ("software.amazon.awssdk", "aws-sdk-java", "2.16.31")
    implementation ("software.amazon.awssdk", "bom", "2.16.31")
    implementation ("software.amazon.awssdk", "s3", "2.16.31")
    implementation ("software.amazon.awssdk", "sdk-core", "2.16.31")


    implementation("com.google.code.gson", "gson", "2.8.6")

    annotationProcessor("io.micronaut.data:micronaut-data-processor")

    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:testcontainers")

    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.postgresql:postgresql")
}


application {
    mainClass.set("com.casadodev.bootloader.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("15")
    targetCompatibility = JavaVersion.toVersion("15")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "15"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "15"
        }
    }


}

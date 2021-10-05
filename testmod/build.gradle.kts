plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    compileOnly(rootProject)
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.spongepowered:mixin:0.8.3")
    compileOnly(files("../libs/leaf.jar"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
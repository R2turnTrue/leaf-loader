plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    //implementation(project(":upstream"))
    implementation("org.spongepowered:mixin:0.8.3")
    compileOnly(files("libs/launchwrapper-1.12.jar"))
    compileOnly(files("libs/leaf-default.jar"))
    implementation("com.google.guava:guava:21.0")
    implementation("org.ow2.asm:asm-tree:6.2")
    implementation("org.ow2.asm:asm-commons:6.2")
    implementation("org.ow2.asm:asm-util:6.2")
    implementation("com.google.code.gson:gson:2.2.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
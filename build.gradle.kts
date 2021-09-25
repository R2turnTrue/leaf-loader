import java.io.*
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `kotlin-dsl`
    `java-library`
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
    compileOnly(files("libs/leaf.jar"))
    implementation("org.reflections:reflections:0.9.12")
    implementation("com.google.guava:guava:21.0")
    implementation("org.ow2.asm:asm-tree:6.2")

    implementation("ch.qos.logback:logback-core:1.2.5")
    implementation("ch.qos.logback:logback-classic:1.2.5")

    implementation("org.ow2.asm:asm-commons:6.2")
    implementation("org.ow2.asm:asm-util:6.2")
    implementation("com.google.code.gson:gson:2.8.8")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

abstract class Setup() : DefaultTask() {

    @org.gradle.api.tasks.Input
    var updateAll: Boolean = false

    @TaskAction
    fun run() {
        val runDir = File("run/")
        if(!runDir.exists())
            runDir.mkdir()
        val libDir = File("libs/")
        if(!libDir.exists())
            libDir.mkdir()
        val downloades = HashMap<File, String>()
        downloades[File(runDir, "leaf.jar")] = "https://raw.githubusercontent.com/NamuTree0345/leaf/main/release.txt"
        downloades[File(runDir, "launchwrapper-1.12.jar")] = "https://raw.githubusercontent.com/NamuTree0345/leaf/main/launchwrapper.txt"
        downloades[File(libDir, "leaf.jar")] = "https://raw.githubusercontent.com/NamuTree0345/leaf/main/release.txt"
        downloades[File(libDir, "launchwrapper-1.12.jar")] = "https://raw.githubusercontent.com/NamuTree0345/leaf/main/launchwrapper.txt"

        fun process(entry: MutableMap.MutableEntry<File, String>) {
            logger.info("[GetURL] from ${entry.key}")

            val url = URL(entry.value)
            val connection = url.openConnection() as java.net.HttpURLConnection

            connection.requestMethod = "GET"
            connection.setRequestProperty("User-Agent", "Mozilla/5.0")

            val buff = BufferedReader(InputStreamReader(connection.inputStream))
            val stringBuffer = StringBuffer()

            var inputLine: String?

            while (buff.readLine().also { inputLine = it } != null) {
                stringBuffer.append(inputLine)
            }

            val OUTPUT_FILE_PATH = entry.key
            val FILE_URL = stringBuffer.toString()

            logger.info("[Download] ${entry.key.absolutePath} <- $FILE_URL")
            URL(FILE_URL).openStream().use { `in` ->
                val imagePath = Paths.get(OUTPUT_FILE_PATH.toURI())
                Files.copy(`in`, imagePath)
            }
        }

        for (entry in downloades) {

            if(updateAll) {
                process(entry)
                continue
            }

            if (!entry.key.exists()) {
                process(entry)
            } else {
                logger.info("[Skip] ${entry.key.absolutePath}")
            }
        }
    }
}

tasks.register<Setup>("setup") {
    group = "leaf"
    updateAll = false
}
tasks.register<Setup>("setupUpdate") {
    group = "leaf"
    updateAll = true
}

tasks.getByName<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveBaseName.set("lml")
}

tasks.register<JavaExec>("runClient") {
    group = "leaf"

    workingDir("run/")
    dependsOn("copyClient")

    classpath("run/lml-${version}-all.jar", "run/leaf.jar", "run/launchwrapper-1.12.jar")

    main = "net.minecraft.launchwrapper.Launch"
    args("--tweakClass", "xyz.namutree0345.lml.LoafLoaderTweaker")
}

tasks.register<Copy>("copyClient") {
    group = "leaf"
    dependsOn(tasks.shadowJar)

    from(File("build/libs/lml-${version}-all.jar"))
    into(File(rootDir, "run"))
}
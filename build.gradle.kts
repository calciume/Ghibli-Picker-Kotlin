plugins {
    kotlin("jvm") version "2.0.10"
    kotlin("plugin.serialization") version "2.0.20"
}

group = "me.calciu"
version = "0.1.0"
tasks.jar {
    manifest.attributes["Main-Class"] = "me.calciu.ghiblipicker.MainKt"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.2")
}

tasks.test {
    useJUnitPlatform()
}
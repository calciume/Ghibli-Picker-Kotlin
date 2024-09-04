plugins {
    kotlin("jvm") version "2.0.10"
}

group = "me.calciu"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
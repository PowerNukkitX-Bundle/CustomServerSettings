plugins {
    id("java")
    id("io.freefair.lombok") version "8.4"
}

group = "org.powernukkitx"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.powernukkitx.org/releases")
    maven("https://repo.opencollab.dev/maven-releases")
    maven("https://repo.opencollab.dev/maven-snapshots")
}

dependencies {
    implementation("org.powernukkitx.protocol:bedrock-codec:3.0.0.Beta7-Debug-SNAPSHOT")
    compileOnly("org.powernukkitx:server:b-migration-SNAPSHOT")
}

tasks.jar {
    archiveFileName.set("CustomServerSettings.jar")
}
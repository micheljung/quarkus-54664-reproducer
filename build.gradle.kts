plugins {
    kotlin("jvm") version "2.3.21" apply false
    kotlin("plugin.allopen") version "2.3.21" apply false
    id("io.quarkus") version "3.36.0" apply false
}

subprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

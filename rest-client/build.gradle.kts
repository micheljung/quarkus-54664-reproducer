// Mirrors the user's "kratos-client" library module:
// - applies io.quarkus for Jandex bean indexing and code generation
// - has quarkus-rest-client-jackson and other extensions
// - depends on :security (like kratos-client depends on security-quarkus)
// - disables Quarkus app build tasks (it's a library, not the final app)
plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    id("io.quarkus")
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
}

val quarkusPlatformVersion = "3.36.0"

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusPlatformVersion"))
    implementation(project(":security"))
    implementation("io.quarkus:quarkus-rest-client-jackson")
    implementation("io.quarkus:quarkus-cache")
    implementation("io.quarkus:quarkus-security")
    implementation("io.quarkus:quarkus-vertx-http")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        javaParameters = true
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

// Library module: do NOT build a full Quarkus application
listOf(tasks.named("quarkusBuild"), tasks.named("quarkusAppPartsBuild")).forEach {
    it.configure { enabled = false }
}

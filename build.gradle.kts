
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(ktorLibs.plugins.ktor)
}

group = "com.danilovmaximov"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(26)
}

val sbeTool = configurations.create("sbeTool")

dependencies {
    implementation(ktorLibs.client.core)
    implementation(ktorLibs.client.okhttp)
    implementation(ktorLibs.client.contentNegotiation)
    implementation(ktorLibs.serialization.kotlinx.json)
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(libs.logback.classic)

    sbeTool("uk.co.real-logic:sbe-tool:1.42.0")

    implementation("org.agrona:agrona:2.7.1")

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}

val generateSbe = tasks.register<JavaExec>("generateSbe") {
    description = "Generate SBE encoders/decoders from Binance schema"
    val schema = layout.projectDirectory.file("src/main/sbe/spot_3_5.xml")
    val outDir = layout.buildDirectory.dir("generated/sources/sbe")
    inputs.file(schema)
    outputs.dir(outDir)
    classpath = sbeTool
    mainClass = "uk.co.real_logic.sbe.SbeTool"
    systemProperty("sbe.output.dir", outDir.get().asFile.path)
    systemProperty("sbe.target.language", "Java")
    args(schema.asFile.path)
}

sourceSets.main { java.srcDir(generateSbe) }

val agronaJvmArgs = listOf("--add-exports=java.base/jdk.internal.misc=ALL-UNNAMED")
application { applicationDefaultJvmArgs = agronaJvmArgs }
tasks.test { jvmArgs(agronaJvmArgs) }

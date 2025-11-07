plugins { kotlin("multiplatform") version "1.9.10" }

kotlin {
    jvm { testRuns["test"].executionTask.configure { useJUnit() } }
    js {
        nodejs()
        binaries.executable()
    }

    sourceSets {
        val commonTest by getting { dependencies { implementation(kotlin("test")) } }
        val jsTest by getting { dependencies { implementation(kotlin("test")) } }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // Set to 17 to match Kotlin
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17" // Use a supported version like 17 or 11
    }
}

tasks.register("test") {
    // dependsOn(":dwf-validator:jsTest")
    dependsOn(":dwf-validator:jvmTest")
}

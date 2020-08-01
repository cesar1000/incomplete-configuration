plugins {
    kotlin("jvm") version "1.3.72"
    `maven-publish`
}

val isLazy = extra.has("lazy")

group = "com.twitter.test"
version = if (isLazy) "0.1.0-lazy" else "0.1.0-direct"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

repositories {
	jcenter()
}

val myConfiguration by configurations.creating

dependencies {
    implementation(kotlin("stdlib"))
    myConfiguration("javax.inject:javax.inject:1")
    if (isLazy) {
        implementation(provider { myConfiguration })
    } else {
        implementation(myConfiguration)
    }
}

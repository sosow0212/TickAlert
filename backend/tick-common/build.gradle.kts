import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.withType<BootJar> {
    enabled = false
}

tasks.withType<Jar> {
    enabled = true
}

dependencies {
    api("io.github.microutils:kotlin-logging-jvm:2.0.11")

    // jasypt
    api("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

    // jwt
    api("io.jsonwebtoken:jjwt-api:0.12.6")
    api("io.jsonwebtoken:jjwt-impl:0.12.6")
    api("io.jsonwebtoken:jjwt-jackson:0.12.6")

    // security-crypto
    api("org.springframework.security:spring-security-crypto")
}

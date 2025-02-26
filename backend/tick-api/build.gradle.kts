import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.withType<BootJar> {
    enabled = false
}

tasks.withType<Jar> {
    enabled = true
}

dependencies {
    implementation(project(":tick-core"))
    implementation(project(":tick-common"))
    testImplementation(testFixtures(project(":tick-core")))
    testImplementation(testFixtures(project(":tick-common")))

    implementation("org.springframework.boot:spring-boot-starter-web")

    // db
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")
}

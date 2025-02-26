package com.api.global.config.multimodule

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan("com.common", "com.core")
class ModuleConfig {
}

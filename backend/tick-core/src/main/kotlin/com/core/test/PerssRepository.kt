package com.core.test

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PerssRepository : JpaRepository<perss, Long> {
}

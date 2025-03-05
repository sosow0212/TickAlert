package com.core.cryptocurrency.infrastructure

import com.core.cryptocurrency.domain.Cryptocurrency
import org.springframework.data.jpa.repository.JpaRepository

interface CryptocurrencyJpaRepository : JpaRepository<Cryptocurrency, Long> {
}

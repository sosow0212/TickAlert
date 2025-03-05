package com.core.cryptocurrency.infrastructure

import org.springframework.stereotype.Repository

@Repository
class CryptocurrencyRepositoryImpl(
    private val cryptocurrencyJpaRepository: CryptocurrencyJpaRepository
) {

}

package com.core.cryptocurrency.infrastructure

import org.springframework.stereotype.Repository

@Repository
class CryptocurrencyPriceRepositoryImpl(
    private val cryptocurrencyPriceJpaRepository: CryptocurrencyPriceJpaRepository
) {

}

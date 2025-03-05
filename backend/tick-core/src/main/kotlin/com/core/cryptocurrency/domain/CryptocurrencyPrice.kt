package com.core.cryptocurrency.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "cryptocurrency_price")
class CryptocurrencyPrice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0L,

    @Column(nullable = false, name = "cryptocurrency_id")
    val cryptocurrencyId: Long,

    @Column(nullable = false, name = "trade_price")
    val tradePrice: Double,

    @Column(nullable = false, name = "low_price")
    val lowPrice: Double,

    @Column(nullable = false, name = "high_price")
    val highPrice: Double,

    @Column(nullable = false, name = "recorded_at")
    val recordedAt: LocalDateTime
) {
}

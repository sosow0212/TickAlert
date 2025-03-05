package com.core.cryptocurrency.domain

import com.core.cryptocurrency.domain.vo.CryptocurrencyPlatform
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cryptocurrency")
class Cryptocurrency(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0L,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "platform")
    val platform: CryptocurrencyPlatform,

    @Column(nullable = false)
    val market: String,

    @Column(nullable = false, name = "korean_name")
    val koreanName: String,

    @Column(nullable = false, name = "english_name")
    val englishName: String,
) {
}

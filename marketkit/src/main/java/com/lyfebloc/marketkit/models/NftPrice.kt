package com.lyfebloc.marketkit.models

import java.math.BigDecimal

data class NftPrice(
    val token: Token,
    val value: BigDecimal
)

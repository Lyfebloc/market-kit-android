package com.lyfebloc.marketkit.managers

import com.lyfebloc.marketkit.models.Exchange
import com.lyfebloc.marketkit.storage.ExchangeStorage

class ExchangeManager(private val storage: ExchangeStorage) {

    fun imageUrlsMap(ids: List<String>): Map<String, String> {
        val exchanges = storage.exchanges(ids)
        val imageUrls = mutableMapOf<String, String>()
        exchanges.forEach {
            imageUrls[it.id] = it.imageUrl
        }
        return imageUrls
    }

    fun handleFetched(exchanges: List<Exchange>) {
        storage.update(exchanges)
    }
}

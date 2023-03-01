package com.lyfebloc.marketkit.managers

import com.lyfebloc.marketkit.models.Post
import com.lyfebloc.marketkit.providers.CryptoCompareProvider
import io.reactivex.Single

class PostManager(
    private val provider: CryptoCompareProvider
) {
    fun postsSingle(): Single<List<Post>> {
        return provider.postsSingle()
    }
}

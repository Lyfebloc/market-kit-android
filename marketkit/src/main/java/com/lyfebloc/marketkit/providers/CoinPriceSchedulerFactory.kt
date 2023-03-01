package com.lyfebloc.marketkit.providers

import com.lyfebloc.marketkit.managers.CoinPriceManager
import com.lyfebloc.marketkit.managers.ICoinPriceCoinUidDataSource
import com.lyfebloc.marketkit.Scheduler

class CoinPriceSchedulerFactory(
    private val manager: CoinPriceManager,
    private val provider: HsProvider
) {
    fun scheduler(currencyCode: String, coinUidDataSource: ICoinPriceCoinUidDataSource): Scheduler {
        val schedulerProvider = CoinPriceSchedulerProvider(currencyCode, manager, provider)
        schedulerProvider.dataSource = coinUidDataSource
        return Scheduler(schedulerProvider)
    }
}

package com.lyfebloc.marketkit.chart

import com.lyfebloc.marketkit.ProviderError
import com.lyfebloc.marketkit.models.ChartInfoKey
import com.lyfebloc.marketkit.chart.scheduler.IChartSchedulerProvider
import com.lyfebloc.marketkit.providers.HsProvider
import io.reactivex.Single

class ChartSchedulerProvider(
    override val retryInterval: Long,
    private val key: ChartInfoKey,
    private val provider: HsProvider,
    private val manager: ChartManager,
    private val indicatorPoints: Int
) : IChartSchedulerProvider {

    override val id = key.toString()

    override val lastSyncTimestamp: Long?
        get() = manager.getLastSyncTimestamp(key)

    override val expirationInterval: Long
        get() = key.periodType.expiration

    override val syncSingle: Single<Unit>
        get() = provider.coinPriceChartSingle(key.coin.uid, key.currencyCode, key.periodType, indicatorPoints)
            .doOnSuccess { response ->
                val points = response.map { it.chartPoint }
                manager.update(points, key)
            }
            .doOnError {
                if (it is ProviderError.NoDataForCoin) {
                    manager.handleNoChartPoints(key)
                }
            }
            .map { }

    override fun notifyExpired() = Unit

}

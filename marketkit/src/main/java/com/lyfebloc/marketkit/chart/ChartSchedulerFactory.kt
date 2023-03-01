package com.lyfebloc.marketkit.chart

import com.lyfebloc.marketkit.models.ChartInfoKey
import com.lyfebloc.marketkit.chart.scheduler.ChartScheduler
import com.lyfebloc.marketkit.providers.HsProvider

class ChartSchedulerFactory(
    private val manager: ChartManager,
    private val provider: HsProvider,
    private val indicatorPoints: Int,
    private val retryInterval: Long = 30
) {

    fun getScheduler(key: ChartInfoKey): ChartScheduler {
        return ChartScheduler(ChartSchedulerProvider(retryInterval, key, provider, manager, indicatorPoints))
    }
}

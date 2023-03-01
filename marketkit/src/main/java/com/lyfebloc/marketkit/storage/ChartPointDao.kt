package com.lyfebloc.marketkit.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lyfebloc.marketkit.models.ChartPointEntity
import com.lyfebloc.marketkit.models.HsPeriodType

@Dao
interface ChartPointDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stats: List<ChartPointEntity>)

    @Query("DELETE FROM ChartPointEntity WHERE coinUid = :coinUid AND currencyCode = :currencyCode AND interval = :interval")
    fun delete(coinUid: String, currencyCode: String, interval: HsPeriodType)

    @Query("SELECT * FROM ChartPointEntity WHERE coinUid = :coinUid AND currencyCode = :currencyCode AND interval = :interval ORDER BY timestamp ASC")
    fun getList(coinUid: String, currencyCode: String, interval: HsPeriodType): List<ChartPointEntity>

}

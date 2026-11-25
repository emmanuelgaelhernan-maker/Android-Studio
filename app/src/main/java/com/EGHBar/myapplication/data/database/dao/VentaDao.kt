package com.EGHBar.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.EGHBar.myapplication.data.database.entity.Venta
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {
    @Query("SELECT * FROM ventas ORDER BY id DESC")
    fun getAllVentas(): Flow<List<Venta>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenta(venta: Venta)
}

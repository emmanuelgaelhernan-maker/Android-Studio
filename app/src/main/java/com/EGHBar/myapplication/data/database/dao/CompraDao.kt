package com.EGHBar.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.EGHBar.myapplication.data.database.entity.Compra
import kotlinx.coroutines.flow.Flow

@Dao
interface CompraDao {
    @Query("SELECT * FROM compras ORDER BY id DESC")
    fun getAllCompras(): Flow<List<Compra>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompra(compra: Compra)
}

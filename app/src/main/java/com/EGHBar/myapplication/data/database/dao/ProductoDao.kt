package com.EGHBar.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.EGHBar.myapplication.data.database.entity.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Query("SELECT * FROM productos")
    fun getAllProductos(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE esOferta = 1")
    fun getOfertas(): Flow<List<Producto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducto(producto: Producto)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(productos: List<Producto>)

    @Delete
    suspend fun deleteProducto(producto: Producto)
}

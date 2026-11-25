package com.EGHBar.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.EGHBar.myapplication.data.database.dao.CompraDao
import com.EGHBar.myapplication.data.database.dao.ProductoDao
import com.EGHBar.myapplication.data.database.dao.UsuarioDao
import com.EGHBar.myapplication.data.database.dao.VentaDao
import com.EGHBar.myapplication.data.database.entity.Compra
import com.EGHBar.myapplication.data.database.entity.Producto
import com.EGHBar.myapplication.data.database.entity.Usuario
import com.EGHBar.myapplication.data.database.entity.Venta

@Database(
    entities = [Producto::class, Compra::class, Usuario::class, Venta::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun compraDao(): CompraDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun ventaDao(): VentaDao
}

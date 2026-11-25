package com.EGHBar.myapplication.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ventas")
data class Venta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val autoNombre: String = "",
    val descripcion: String = "",
    val precio: Double = 0.0,
    val vendedor: String = "",
    val fecha: String = ""
)

package com.EGHBar.myapplication.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val precio: Double = 0.0,
    val esOferta: Boolean = false
)

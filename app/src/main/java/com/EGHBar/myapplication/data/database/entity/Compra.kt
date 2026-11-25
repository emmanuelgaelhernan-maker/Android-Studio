package com.EGHBar.myapplication.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "compras")
data class Compra(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val autoNombre: String = "",
    val precio: Double = 0.0,
    val fecha: String = ""
)

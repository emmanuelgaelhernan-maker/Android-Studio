package com.EGHBar.myapplication.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey val id: Int = 1,
    val nombre: String = "Juan Pérez",
    val email: String = "juan.perez@email.com",
    val telefono: String = "+52 555 123 4567",
    val direccion: String = "Ciudad de México, México",
    val password: String = "1234"
)

package com.EGHBar.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.EGHBar.myapplication.data.database.entity.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios WHERE id = 1 LIMIT 1")
    fun getUsuario(): Flow<Usuario?>

    @Query("SELECT * FROM usuarios WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): Usuario?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUsuario(usuario: Usuario)

    // Solo inserta si no existe; no sobrescribe un perfil ya guardado
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIfNotExists(usuario: Usuario)
}

package com.EGHBar.myapplication.di

import android.content.Context
import androidx.room.Room
import com.EGHBar.myapplication.data.database.AppDatabase
import com.EGHBar.myapplication.data.database.dao.CompraDao
import com.EGHBar.myapplication.data.database.dao.ProductoDao
import com.EGHBar.myapplication.data.database.dao.UsuarioDao
import com.EGHBar.myapplication.data.database.dao.VentaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "auto_sales_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideProductoDao(database: AppDatabase): ProductoDao {
        return database.productoDao()
    }

    @Provides
    fun provideCompraDao(database: AppDatabase): CompraDao {
        return database.compraDao()
    }

    @Provides
    fun provideUsuarioDao(database: AppDatabase): UsuarioDao {
        return database.usuarioDao()
    }

    @Provides
    fun provideVentaDao(database: AppDatabase): VentaDao {
        return database.ventaDao()
    }
}

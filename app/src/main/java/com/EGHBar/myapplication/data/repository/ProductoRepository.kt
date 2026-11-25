package com.EGHBar.myapplication.data.repository

import com.EGHBar.myapplication.data.database.dao.CompraDao
import com.EGHBar.myapplication.data.database.dao.ProductoDao
import com.EGHBar.myapplication.data.database.dao.UsuarioDao
import com.EGHBar.myapplication.data.database.dao.VentaDao
import com.EGHBar.myapplication.data.database.entity.Compra
import com.EGHBar.myapplication.data.database.entity.Producto
import com.EGHBar.myapplication.data.database.entity.Usuario
import com.EGHBar.myapplication.data.database.entity.Venta
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductoRepository @Inject constructor(
    private val productoDao: ProductoDao,
    private val compraDao: CompraDao,
    private val usuarioDao: UsuarioDao,
    private val ventaDao: VentaDao
) {
    fun getAllProductos(): Flow<List<Producto>> = productoDao.getAllProductos()

    fun getOfertas(): Flow<List<Producto>> = productoDao.getOfertas()

    fun getCompras(): Flow<List<Compra>> = compraDao.getAllCompras()

    fun getUsuario(): Flow<Usuario?> = usuarioDao.getUsuario()

    fun getVentas(): Flow<List<Venta>> = ventaDao.getAllVentas()

    suspend fun iniciarSesion(email: String, password: String): Boolean {
        // Asegura que exista el usuario por defecto antes de validar
        usuarioDao.insertIfNotExists(Usuario())
        return usuarioDao.login(email.trim(), password) != null
    }

    suspend fun guardarUsuario(usuario: Usuario) {
        usuarioDao.insertOrUpdateUsuario(usuario)
    }

    suspend fun realizarCompra(producto: Producto) {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val fechaActual = sdf.format(Date())
        val compra = Compra(
            autoNombre = producto.nombre,
            precio = producto.precio,
            fecha = fechaActual
        )
        compraDao.insertCompra(compra)
    }

    suspend fun publicarVenta(autoNombre: String, descripcion: String, precio: Double, vendedor: String) {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val fechaActual = sdf.format(Date())

        val venta = Venta(
            autoNombre = autoNombre,
            descripcion = descripcion,
            precio = precio,
            vendedor = vendedor,
            fecha = fechaActual
        )
        // La venta ya se muestra en la sección "Publicaciones de vendedores" de Ofertas,
        // por eso no se duplica en la tabla de productos.
        ventaDao.insertVenta(venta)
    }

    suspend fun initDefaultProductosIfEmpty() {
        val initialList = listOf(
            Producto(id = 1, nombre = "Sedán Ejecutivo 2024", descripcion = "Elegante auto para ciudad. Motor 2.0L Turbo, asientos de piel.", precio = 28500.0, esOferta = true),
            Producto(id = 2, nombre = "SUV Familiar 4x4", descripcion = "Espaciosa SUV con tracción en las 4 ruedas y cámara 360°.", precio = 39900.0, esOferta = true),
            Producto(id = 3, nombre = "Deportivo GT Turbo", descripcion = "Diseño aerodinámico, 400 HP, aceleración 0-100 en 3.8s.", precio = 55000.0, esOferta = true),
            Producto(id = 4, nombre = "Camioneta Pickup Heavy Duty", descripcion = "Gran capacidad de carga y remolque para trabajo pesado.", precio = 45000.0, esOferta = false),
            Producto(id = 5, nombre = "Hatchback Urbano Eco", descripcion = "Bajo consumo de combustible, ideal para tráfico en ciudad.", precio = 18900.0, esOferta = true)
        )
        productoDao.insertAll(initialList)

        // Inicializar usuario por defecto solo si no existe (no sobrescribe el perfil guardado)
        usuarioDao.insertIfNotExists(Usuario())
    }
}

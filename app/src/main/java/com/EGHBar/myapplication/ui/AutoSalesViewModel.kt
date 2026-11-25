package com.EGHBar.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.EGHBar.myapplication.data.database.entity.Compra
import com.EGHBar.myapplication.data.database.entity.Producto
import com.EGHBar.myapplication.data.database.entity.Usuario
import com.EGHBar.myapplication.data.database.entity.Venta
import com.EGHBar.myapplication.data.repository.ProductoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoSalesViewModel @Inject constructor(
    private val repository: ProductoRepository
) : ViewModel() {

    val ofertas: StateFlow<List<Producto>> = repository.getOfertas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val todosProductos: StateFlow<List<Producto>> = repository.getAllProductos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val compras: StateFlow<List<Compra>> = repository.getCompras()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val usuario: StateFlow<Usuario?> = repository.getUsuario()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val ventas: StateFlow<List<Venta>> = repository.getVentas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.initDefaultProductosIfEmpty()
        }
    }

    fun iniciarSesion(email: String, password: String, onResultado: (Boolean) -> Unit) {
        viewModelScope.launch {
            onResultado(repository.iniciarSesion(email, password))
        }
    }

    fun comprarAuto(producto: Producto) {
        viewModelScope.launch {
            repository.realizarCompra(producto)
        }
    }

    fun publicarVenta(nombre: String, descripcion: String, precio: Double, vendedor: String) {
        viewModelScope.launch {
            repository.publicarVenta(nombre, descripcion, precio, vendedor)
        }
    }

    fun guardarPerfil(nombre: String, email: String, telefono: String, direccion: String) {
        viewModelScope.launch {
            // Se parte del usuario actual para conservar la contraseña
            val usuarioActualizado = (usuario.value ?: Usuario()).copy(
                id = 1,
                nombre = nombre,
                email = email,
                telefono = telefono,
                direccion = direccion
            )
            repository.guardarUsuario(usuarioActualizado)
        }
    }
}

package com.EGHBar.myapplication.Compasbles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.EGHBar.myapplication.ui.AutoSalesViewModel

@Composable
fun BoxExample(
    viewModel: AutoSalesViewModel = hiltViewModel(),
    onLogout: () -> Unit = {}
) {
    var ventanaActual by rememberSaveable { mutableStateOf("inicio") }

    val ofertas by viewModel.todosProductos.collectAsState()
    val compras by viewModel.compras.collectAsState()
    val usuario by viewModel.usuario.collectAsState()
    val ventas by viewModel.ventas.collectAsState()

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column {
            RowExample(
                ventanaActual = ventanaActual,
                onInicioClick = { ventanaActual = "inicio" },
                onOfertasClick = { ventanaActual = "ofertas" },
                onVentasClick = { ventanaActual = "ventas" },
                onPerfilClick = { ventanaActual = "perfil" }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (ventanaActual) {
                "inicio" -> ImageExample()
                "ofertas" -> OfertasScreen(
                    ofertas = ofertas,
                    compras = compras,
                    ventas = ventas,
                    onComprar = { auto ->
                        viewModel.comprarAuto(auto)
                    }
                )
                "ventas" -> VentasScreen(
                    ventas = ventas,
                    onPublicarVenta = { nombre, descripcion, precio, vendedor ->
                        viewModel.publicarVenta(nombre, descripcion, precio, vendedor)
                    }
                )
                "perfil" -> PerfilScreen(
                    usuario = usuario,
                    onGuardarPerfil = { nombre, email, telefono, direccion ->
                        viewModel.guardarPerfil(nombre, email, telefono, direccion)
                    },
                    onCerrarSesion = onLogout
                )
            }
        }
    }
}

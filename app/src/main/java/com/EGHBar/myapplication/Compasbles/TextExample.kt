package com.EGHBar.myapplication.Compasbles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.EGHBar.myapplication.R
import com.EGHBar.myapplication.data.database.entity.Compra
import com.EGHBar.myapplication.data.database.entity.Producto
import com.EGHBar.myapplication.data.database.entity.Venta
import java.util.Locale

fun obtenerImagenProducto(nombre: String): Int {
    return when {
        nombre.contains("Sedán", ignoreCase = true) || nombre.contains("sedan", ignoreCase = true) -> R.drawable.sedanejecutivo
        nombre.contains("SUV", ignoreCase = true) -> R.drawable.suvfamiliar
        nombre.contains("Deportivo", ignoreCase = true) -> R.drawable.deportivo
        else -> R.drawable.logoinicio
    }
}

@Composable
fun OfertasScreen(
    ofertas: List<Producto> = emptyList(),
    compras: List<Compra> = emptyList(),
    ventas: List<Venta> = emptyList(),
    onComprar: (Producto) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.titulo_ofertas),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.subtitulo_ofertas),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        items(ofertas) { producto ->
            val imagenRes = obtenerImagenProducto(producto.nombre)

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imagenRes),
                        contentDescription = producto.nombre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = producto.nombre,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = producto.descripcion,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "$${String.format(Locale.US, "%.2f", producto.precio)} USD",
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { onComprar(producto) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = stringResource(id = R.string.btn_apartar_comprar),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(stringResource(id = R.string.btn_apartar_comprar), fontSize = 13.sp)
                            }
                        }
                    }
                }
            }
        }

        if (ventas.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.titulo_publicaciones_vendedores, ventas.size),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(ventas) { venta ->
                val imagenRes = obtenerImagenProducto(venta.autoNombre)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = imagenRes),
                            contentDescription = venta.autoNombre,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = venta.autoNombre,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = venta.descripcion,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = stringResource(id = R.string.label_vendedor, venta.vendedor),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$${String.format(Locale.US, "%.2f", venta.precio)} USD",
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    val productoVenta = Producto(
                                        nombre = venta.autoNombre,
                                        descripcion = venta.descripcion,
                                        precio = venta.precio
                                    )
                                    onComprar(productoVenta)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = stringResource(id = R.string.btn_apartar_comprar),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(stringResource(id = R.string.btn_apartar_comprar), fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }
            }
        }

        if (compras.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.titulo_mis_compras, compras.size),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(compras) { compra ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = compra.autoNombre,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = stringResource(id = R.string.label_fecha, compra.fecha),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = "$${String.format(Locale.US, "%.2f", compra.precio)}",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextExample() {
    OfertasScreen(
        ofertas = listOf(
            Producto(id = 1, nombre = "Sedán Ejecutivo 2024", descripcion = "Auto cómodo y elegante para ciudad", precio = 28500.0, esOferta = true),
            Producto(id = 2, nombre = "SUV Familiar 4x4", descripcion = "Espaciosa SUV con tracción 4x4", precio = 39900.0, esOferta = true)
        )
    )
}

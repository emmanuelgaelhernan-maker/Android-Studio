package com.EGHBar.myapplication.Compasbles

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.EGHBar.myapplication.R
import com.EGHBar.myapplication.data.database.entity.Venta
import java.util.Locale

@Composable
fun VentasScreen(
    ventas: List<Venta> = emptyList(),
    onPublicarVenta: (nombre: String, descripcion: String, precio: Double, vendedor: String) -> Unit = { _, _, _, _ -> }
) {
    var autoNombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precioTexto by remember { mutableStateOf("") }
    var vendedorNombre by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    val textoErrorCampos = stringResource(id = R.string.error_campos_incompletos)

    LazyColumn(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.titulo_publicar_venta),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = autoNombre,
                        onValueChange = { autoNombre = it },
                        label = { Text(stringResource(id = R.string.label_auto_nombre)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text(stringResource(id = R.string.label_auto_descripcion)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = precioTexto,
                        onValueChange = { precioTexto = it },
                        label = { Text(stringResource(id = R.string.label_auto_precio)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = vendedorNombre,
                        onValueChange = { vendedorNombre = it },
                        label = { Text(stringResource(id = R.string.label_vendedor_nombre)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    if (mensajeError.isNotEmpty()) {
                        Text(
                            text = mensajeError,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Button(
                        onClick = {
                            val precio = precioTexto.toDoubleOrNull()
                            if (autoNombre.isBlank() || descripcion.isBlank() || precio == null || precio <= 0.0 || vendedorNombre.isBlank()) {
                                mensajeError = textoErrorCampos
                            } else {
                                onPublicarVenta(autoNombre.trim(), descripcion.trim(), precio, vendedorNombre.trim())
                                autoNombre = ""
                                descripcion = ""
                                precioTexto = ""
                                vendedorNombre = ""
                                mensajeError = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.btn_subir_venta),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(stringResource(id = R.string.btn_subir_venta))
                        }
                    }
                }
            }
        }

        if (ventas.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.titulo_ventas_recientes, ventas.size),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(ventas) { venta ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = venta.autoNombre,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "$${String.format(Locale.US, "%.2f", venta.precio)} USD",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = venta.descripcion,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(id = R.string.label_vendedor, venta.vendedor),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = stringResource(id = R.string.label_fecha, venta.fecha),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

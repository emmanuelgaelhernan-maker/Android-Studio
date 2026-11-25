package com.EGHBar.myapplication.Compasbles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.EGHBar.myapplication.R
import com.EGHBar.myapplication.data.database.entity.Usuario

@Composable
fun PerfilScreen(
    usuario: Usuario?,
    onGuardarPerfil: (nombre: String, email: String, telefono: String, direccion: String) -> Unit = { _, _, _, _ -> },
    onCerrarSesion: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var mensajeConfirmacion by remember { mutableStateOf("") }

    val textoConfirmacion = stringResource(id = R.string.msg_perfil_actualizado)

    LaunchedEffect(usuario) {
        usuario?.let {
            nombre = it.nombre
            email = it.email
            telefono = it.telefono
            direccion = it.direccion
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = stringResource(id = R.string.desc_foto_perfil),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.titulo_perfil),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text(stringResource(id = R.string.label_nombre_completo)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(id = R.string.label_correo_electronico)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text(stringResource(id = R.string.label_telefono_contacto)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text(stringResource(id = R.string.label_direccion)) },
                    modifier = Modifier.fillMaxWidth()
                )

                if (mensajeConfirmacion.isNotEmpty()) {
                    Text(
                        text = mensajeConfirmacion,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Button(
                    onClick = {
                        onGuardarPerfil(nombre, email, telefono, direccion)
                        mensajeConfirmacion = textoConfirmacion
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = stringResource(id = R.string.btn_guardar_perfil),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(stringResource(id = R.string.btn_guardar_perfil))
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = onCerrarSesion,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = stringResource(id = R.string.btn_cerrar_sesion),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(stringResource(id = R.string.btn_cerrar_sesion))
                    }
                }
            }
        }
    }
}

package com.EGHBar.myapplication.Compasbles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.EGHBar.myapplication.R
import com.EGHBar.myapplication.ui.AutoSalesViewModel

/**
 * Pantalla de inicio de sesión conectada a la base de datos.
 */
@Composable
fun LoginScreen(
    viewModel: AutoSalesViewModel = hiltViewModel(),
    onLoginExitoso: () -> Unit
) {
    var cargando by rememberSaveable { mutableStateOf(false) }
    var errorCredenciales by rememberSaveable { mutableStateOf(false) }

    LoginContent(
        cargando = cargando,
        errorCredenciales = errorCredenciales,
        onIniciarSesion = { email, password ->
            cargando = true
            errorCredenciales = false
            viewModel.iniciarSesion(email, password) { exito ->
                cargando = false
                if (exito) onLoginExitoso() else errorCredenciales = true
            }
        }
    )
}

/**
 * UI sin estado del login (sin ViewModel), útil para previews.
 */
@Composable
fun LoginContent(
    cargando: Boolean = false,
    errorCredenciales: Boolean = false,
    onIniciarSesion: (email: String, password: String) -> Unit = { _, _ -> }
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var mostrarPassword by rememberSaveable { mutableStateOf(false) }
    var camposVacios by rememberSaveable { mutableStateOf(false) }

    val intentarLogin = {
        if (email.isBlank() || password.isBlank()) {
            camposVacios = true
        } else {
            camposVacios = false
            onIniciarSesion(email, password)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoinicio),
            contentDescription = stringResource(id = R.string.desc_logo),
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login_bienvenido),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(id = R.string.login_subtitulo),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(id = R.string.login_email)) },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(id = R.string.login_password)) },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { mostrarPassword = !mostrarPassword }) {
                            Icon(
                                imageVector = if (mostrarPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = stringResource(
                                    id = if (mostrarPassword) R.string.login_ocultar_password else R.string.login_mostrar_password
                                )
                            )
                        }
                    },
                    singleLine = true,
                    visualTransformation = if (mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { intentarLogin() }),
                    modifier = Modifier.fillMaxWidth()
                )

                if (camposVacios || errorCredenciales) {
                    Text(
                        text = stringResource(
                            id = if (camposVacios) R.string.login_error_vacios else R.string.login_error_credenciales
                        ),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = intentarLogin,
                    enabled = !cargando,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    if (cargando) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text(stringResource(id = R.string.login_entrar), fontSize = 16.sp)
                    }
                }

                Text(
                    text = stringResource(id = R.string.login_demo),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    LoginContent()
}

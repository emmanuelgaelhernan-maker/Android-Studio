package com.EGHBar.myapplication.Compasbles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.EGHBar.myapplication.R

@Preview(showBackground = true)
@Composable
fun ColumnExample(onLogin: () -> Unit = {}) {
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoinicio),
            contentDescription = stringResource(id = R.string.desc_logo),
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text(stringResource(id = R.string.login_usuario)) }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.login_password)) },
            visualTransformation = PasswordVisualTransformation()
        )

        if (mostrarError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.error_campos_incompletos),
                color = MaterialTheme.colorScheme.error,
                fontSize = 13.sp
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        ButtonExample(
            onClick = {
                if (usuario.isBlank() || password.isBlank()) {
                    mostrarError = true
                } else {
                    mostrarError = false
                    onLogin()
                }
            },
            texto = stringResource(id = R.string.login_entrar)
        )
    }
}

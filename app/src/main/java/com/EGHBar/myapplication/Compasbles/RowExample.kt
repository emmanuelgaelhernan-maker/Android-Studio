package com.EGHBar.myapplication.Compasbles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.EGHBar.myapplication.R

@Composable
fun NavButton(
    texto: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isSelected) {
        Button(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = texto,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = texto,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 2.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = texto,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = texto,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowExample(
    ventanaActual: String = "inicio",
    onInicioClick: () -> Unit = {},
    onOfertasClick: () -> Unit = {},
    onVentasClick: () -> Unit = {},
    onPerfilClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        NavButton(
            texto = stringResource(id = R.string.menu_inicio),
            icon = Icons.Default.Home,
            isSelected = ventanaActual == "inicio",
            onClick = onInicioClick,
            modifier = Modifier.weight(1f)
        )
        NavButton(
            texto = stringResource(id = R.string.menu_ofertas),
            icon = Icons.Default.ShoppingCart,
            isSelected = ventanaActual == "ofertas",
            onClick = onOfertasClick,
            modifier = Modifier.weight(1f)
        )
        NavButton(
            texto = stringResource(id = R.string.menu_ventas),
            icon = Icons.Default.AddCircle,
            isSelected = ventanaActual == "ventas",
            onClick = onVentasClick,
            modifier = Modifier.weight(1f)
        )
        NavButton(
            texto = stringResource(id = R.string.menu_perfil),
            icon = Icons.Default.Person,
            isSelected = ventanaActual == "perfil",
            onClick = onPerfilClick,
            modifier = Modifier.weight(1f)
        )
    }
}

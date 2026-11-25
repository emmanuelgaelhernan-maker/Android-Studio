package com.EGHBar.myapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Rojo,
    secondary = Rojointe,
    tertiary = Gris,
    background = Negro,
    surface = Gris,
    onPrimary = Blanco,
    onSecondary = Blanco,
    onBackground = Blanco,
    onSurface = Blanco,
    surfaceVariant = Gris,
    onSurfaceVariant = Grisclaro
)

private val LightColorScheme = lightColorScheme(
    primary = Rojo,
    secondary = Rojointe,
    tertiary = Gris,
    background = Grisclaro,
    surface = Blanco,
    onPrimary = Blanco,
    onSecondary = Blanco,
    onBackground = Negro,
    onSurface = Negro,
    surfaceVariant = Grisclaro,
    onSurfaceVariant = Gris
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

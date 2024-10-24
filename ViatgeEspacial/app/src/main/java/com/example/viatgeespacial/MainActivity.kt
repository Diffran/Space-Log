package com.example.viatgeespacial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.viatgeespacial.ui.theme.ViatgeEspacialTheme
import com.example.viatgeespacial.vistes.FormulariEspai

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViatgeEspacialTheme {
                var formulari = FormulariEspai()
                formulari.FormulariEspai()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun FormulariEspaiPreview() {
        ViatgeEspacialTheme {
            FormulariEspai()
        }
    }
}



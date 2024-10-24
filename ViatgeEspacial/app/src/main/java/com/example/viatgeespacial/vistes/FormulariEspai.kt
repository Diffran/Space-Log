package com.example.viatgeespacial.vistes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.viatgeespacial.model.RegistroModel

class FormulariEspai {

    @SuppressLint("NotConstructor")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FormulariEspai(){
        var currentStep by remember { mutableStateOf(1) }
        var registre by remember { mutableStateOf(RegistroModel()) }
        var vistes = VistesFormulariResum()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Viatge Espacial",
                            fontWeight = FontWeight.Bold,
                            color = Color.LightGray
                        )
                    },
                    colors = TopAppBarDefaults.run {
                        largeTopAppBarColors(
                            containerColor = Color.DarkGray
                        )
                    }
                )
            }
        ){innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                color = Color.Black
            ){
                when (currentStep){
                    1 ->vistes.VistaFormulari(
                        preguntaId = 1,
                        onClickAction = {
                            if(registre.nom.isNotEmpty())
                                currentStep++
                        },
                        onEntradaChange = {registre.nom = it},
                        modifier = Modifier.fillMaxSize()
                    )
                    2 -> vistes.VistaFormulari(
                        preguntaId = 2,
                        onEntradaChange = { registre.edat = it.toIntOrNull() ?: 0 },
                        onClickAction = {
                            if(registre.edat > 0) currentStep++ },
                        modifier = Modifier.fillMaxSize()
                    )
                    3 -> vistes.VistaFormulari(
                        preguntaId = 3,
                        onClickAction = { if(registre.desti.isNotEmpty())currentStep++ },
                        onEntradaChange = { registre.desti = it },
                        modifier = Modifier.fillMaxSize()
                    )
                    4 -> vistes.VistaResum(
                        registre = registre,
                        onClickAction = { currentStep = 1 })
                }
            }
        }
    }
}
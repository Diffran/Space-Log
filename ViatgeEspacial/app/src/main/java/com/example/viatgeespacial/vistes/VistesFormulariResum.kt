package com.example.viatgeespacial.vistes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viatgeespacial.R
import com.example.viatgeespacial.model.RegistroModel
import kotlin.system.exitProcess

class VistesFormulariResum {

    @Composable
    fun VistaFormulari(
        preguntaId: Int,
        onClickAction: () -> Unit,
        onEntradaChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        var entrada by remember { mutableStateOf("") }

        Box(modifier = modifier)
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(
                        when (preguntaId) {
                            1 -> R.string.textNom
                            2 -> R.string.textEdat
                            3 -> R.string.textDesti
                            else -> R.string.textResum
                        }
                    ),
                    fontSize = 20.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(32.dp))

                when (preguntaId) {
                    1, 2 -> TextField(
                        value = entrada,
                        onValueChange = { nuevoValor ->
                            entrada = nuevoValor
                            onEntradaChange(nuevoValor)
                        },
                        placeholder = { Text("introdueix la resposta") }
                    )

                    3 -> DropdownDesti(onEntradaChange)
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onClickAction,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = stringResource(R.string.seguent))
                }
            }
        }
    }


    @Composable
    fun VistaResum(registre: RegistroModel, onClickAction: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.textResum),
                fontSize = 30.sp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(horizontal = 16.dp)
                    .background(
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    Text(text = "Nom: ${registre.nom}", fontSize = 20.sp, color = Color.White)
                    Text(text = "Edat: ${registre.edat}", fontSize = 20.sp, color = Color.White)
                    Text(text = "Desti: ${registre.desti}", fontSize = 20.sp, color = Color.White)

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick =  onClickAction ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = stringResource(R.string.back))
                    }
                }

            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {exitProcess(0)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "EXIT")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DropdownDesti(onEntradaChange: (String) -> Unit) {
        val opcions = listOf(
            stringResource(R.string.opcio_1),
            stringResource(R.string.opcio_2),
            stringResource(R.string.opcio_3),
            stringResource(R.string.opcio_4)
        )

        var opcioSeleccionada by remember { mutableStateOf(opcions[0]) }
        var isExpanded by remember { mutableStateOf(false) }

        //TOT AIXO ES PER FER EL DROPDOWN MACU I QUE QUEDI BE...
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded })
            {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = opcioSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
                )

                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    opcions.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = {
                                opcioSeleccionada = opcions[index]
                                onEntradaChange(opcioSeleccionada)
                                isExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }
    }
}
package com.example.helloworld.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloWorldTheme

/**
 * MINGGU 1 — Pemrograman Mobile Android
 * TUGAS: Form Sederhana (Nama & NIM/NIP)
 */
@Composable
fun GreetingScreen() {

    // ==========================================
    // STATE MANAGEMENT
    // ==========================================
    var name by remember { mutableStateOf("") }
    var idNumber by remember { mutableStateOf("") }
    var submittedName by remember { mutableStateOf("") }
    var submittedId by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    // State untuk Validasi (Bonus)
    val isNameValid = name.isNotBlank()
    val isIdNumeric = idNumber.all { it.isDigit() } && idNumber.isNotBlank()
    val canSubmit = isNameValid && isIdNumeric

    // ==========================================
    // UI LAYOUT
    // ==========================================
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()), // Support layar kecil/keyboard muncul
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // ── Icon Android ──
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "User Icon",
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ── Judul ──
        Text(
            text = "Form Pendaftaran",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Minggu 1 — Tugas Pemrograman Mobile",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )

        // ── Card Form ──
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Input Nama Lengkap
                OutlinedTextField(
                    value = name,
                    onValueChange = { 
                        name = it
                        isSubmitted = false 
                    },
                    label = { Text("Nama Lengkap") },
                    placeholder = { Text("Contoh: Hikmal Akbar") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    isError = name.isEmpty() && isSubmitted.not(), // Indikasi error jika kosong
                    supportingText = {
                        if (name.isEmpty()) Text("Nama tidak boleh kosong")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Input NIM/NIP
                OutlinedTextField(
                    value = idNumber,
                    onValueChange = { 
                        if (it.all { char -> char.isDigit() }) idNumber = it
                        isSubmitted = false 
                    },
                    label = { Text("NIM / NIP") },
                    placeholder = { Text("Contoh: 2100123") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    isError = !idNumber.all { it.isDigit() } || (idNumber.isEmpty() && isSubmitted.not()),
                    supportingText = {
                        if (idNumber.isNotEmpty() && !idNumber.all { it.isDigit() }) {
                            Text("NIM harus berupa angka")
                        } else if (idNumber.isEmpty()) {
                            Text("NIM tidak boleh kosong")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Button Submit
                Button(
                    onClick = { 
                        submittedName = name
                        submittedId = idNumber
                        isSubmitted = true 
                    },
                    enabled = canSubmit, // Validasi Bonus: Button aktif jika valid
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Submit Data",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Output Result ──
        AnimatedVisibility(
            visible = isSubmitted,
            enter = fadeIn() + slideInVertically()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Result",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Data Terkirim:",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Nama: $submittedName",
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "NIM/NIP: $submittedId",
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Form Screen Preview")
@Composable
fun GreetingScreenPreview() {
    HelloWorldTheme {
        GreetingScreen()
    }
}

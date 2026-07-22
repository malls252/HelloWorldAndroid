package com.example.helloworld.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloWorldTheme

/**
 * MINGGU 1 — Pemrograman Mobile Android
 *
 * GreetingScreen adalah Composable Function utama.
 *
 * Konsep yang dipraktikkan di sini:
 * 1. @Composable annotation
 * 2. State management dengan remember + mutableStateOf
 * 3. Layout dengan Column, Row, Spacer
 * 4. Modifier untuk styling
 * 5. Material3 components (TextField, Button, Card, Text)
 * 6. AnimatedVisibility untuk animasi sederhana
 */
@Composable
fun GreetingScreen() {

    // ==========================================
    // STATE MANAGEMENT
    // ==========================================
    // 'remember' menjaga state agar tidak reset saat recomposition
    // 'mutableStateOf' membuat state yang reaktif — UI otomatis update saat berubah
    var name by remember { mutableStateOf("") }
    var isGreeted by remember { mutableStateOf(false) }

    // ==========================================
    // UI LAYOUT
    // ==========================================
    Column(
        modifier = Modifier
            .fillMaxSize()                  // Isi seluruh layar
            .padding(24.dp),               // Padding 24dp di semua sisi
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // ── Icon Android ──
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Android Logo",
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ── Judul ──
        Text(
            text = "Hello World!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Pemrograman Mobile Android — Minggu 1",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )

        // ── Card Input ──
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Siapa namamu?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // OutlinedTextField dengan onValueChange untuk state update
                OutlinedTextField(
                    value = name,
                    onValueChange = { newValue ->
                        // Setiap kali input berubah, state 'name' diperbarui
                        // Ini memicu recomposition pada semua Composable yang membaca 'name'
                        name = newValue
                        isGreeted = false
                    },
                    label = { Text("Masukkan nama kamu") },
                    placeholder = { Text("Contoh: Budi Santoso") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Button menggunakan lambda untuk onClick
                Button(
                    onClick = { isGreeted = true },
                    enabled = name.isNotBlank(), // Disable jika nama kosong
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Sapa Aku! 👋",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Greeting Result dengan AnimatedVisibility ──
        // AnimatedVisibility hanya muncul saat isGreeted = true
        AnimatedVisibility(
            visible = isGreeted && name.isNotBlank(),
            enter = fadeIn() + slideInVertically()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "🎉", fontSize = 40.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Halo, $name!",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Selamat datang di Android Development!\nMari belajar bersama 🚀",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

// Preview untuk melihat UI di Android Studio tanpa emulator
@Preview(showBackground = true, name = "Greeting Screen Preview")
@Composable
fun GreetingScreenPreview() {
    HelloWorldTheme {
        GreetingScreen()
    }
}

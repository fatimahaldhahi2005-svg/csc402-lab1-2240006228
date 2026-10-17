package com.example.myapplicationlab2



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var greetingMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Interactive Greeting",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                greetingMessage = if (name.isNotBlank()) {
                    "Hello, $name! Welcome to CSC 402."
                } else {
                    "Please enter a valid name."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (greetingMessage.isNotEmpty()) {
            Text(
                text = greetingMessage,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
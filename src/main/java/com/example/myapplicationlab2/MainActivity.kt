package com.example.myapplicationlab2



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.myapplicationlab2.ui.theme.MyApplicationLab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationLab2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // عرض شاشة تتبع المهام (Task 11)
                    TaskTrackerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
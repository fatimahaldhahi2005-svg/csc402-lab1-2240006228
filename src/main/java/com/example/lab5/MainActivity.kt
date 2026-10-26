package com.example.lab5




import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object CourseList

@Serializable
object CollegeWebsite

@Serializable
data class CourseDetail(val code: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CourseApp()
            }
        }
    }
}

@Composable
fun CourseApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        // Home Screen
        composable<Home> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "CSC 402 - Lab 5",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text("Welcome to the Courses App")

                Button(
                    onClick = {
                        navController.navigate(CollegeWebsite)
                    }
                ) {
                    Text("College Website")
                }

                Button(
                    onClick = {
                        navController.navigate(CourseList)
                    }
                ) {
                    Text("Browse Courses")
                }
            }
        }

        // College Website Screen
        composable<CollegeWebsite> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                OutlinedButton(
                    modifier = Modifier.padding(8.dp),
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Back")
                }

                CollegeWebsiteScreen()
            }
        }

        // Course List Screen
        composable<CourseList> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Course List",
                    style = MaterialTheme.typography.headlineMedium
                )

                val courses = listOf(
                    "CSC 402",
                    "CSC 231",
                    "CSC 311"
                )

                courses.forEach { courseCode ->
                    Button(
                        onClick = {
                            navController.navigate(
                                CourseDetail(code = courseCode)
                            )
                        }
                    ) {
                        Text(courseCode)
                    }
                }

                OutlinedButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Back")
                }
            }
        }

        // Course Detail Screen
        composable<CourseDetail> { backStackEntry ->
            val detail: CourseDetail = backStackEntry.toRoute()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Course Details",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Course Code: ${detail.code}",
                    style = MaterialTheme.typography.titleLarge
                )

                Button(
                    onClick = {
                        val shareIntent = android.content.Intent(
                            android.content.Intent.ACTION_SEND
                        ).apply {
                            type = "text/plain"
                            putExtra(
                                android.content.Intent.EXTRA_TEXT,
                                "Course: ${detail.code}"
                            )
                        }

                        val chooser = android.content.Intent.createChooser(
                            shareIntent,
                            "Share Course"
                        )

                        navController.context.startActivity(chooser)
                    }
                ) {
                    Text("Share Course")
                }

                OutlinedButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Back")
                }
            }
        }
    }
}

@Composable
fun CollegeWebsiteScreen() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true

                loadUrl(
                    "https://www.iau.edu.sa/en/colleges/college-science-and-humanities-jubail"
                )
            }
        }
    )
}
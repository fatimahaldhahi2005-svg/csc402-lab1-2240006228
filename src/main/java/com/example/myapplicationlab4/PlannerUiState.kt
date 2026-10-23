package com.example.myapplicationlab4




import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class PlannerUiState(
    val greeting: String = "Hello, Student!",
    val totalTasks: Int = 7,
    val completedTasks: Int = 0
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onCompleteTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = uiState.greeting,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Study Planner",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Total study tasks: ${uiState.totalTasks}"
        )

        Text(
            text = "${uiState.completedTasks} of ${uiState.totalTasks} done"
        )

        Button(
            onClick = onCompleteTask,
            enabled = uiState.completedTasks < uiState.totalTasks
        ) {
            Text(
                text = if (
                    uiState.completedTasks == uiState.totalTasks
                ) {
                    "All Tasks Completed"
                } else {
                    "Complete a Study Task"
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "No Tasks Completed"
)
@Composable
private fun PlannerHomePreview() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                greeting = "Hello, Student!",
                totalTasks = 7,
                completedTasks = 0
            ),
            onCompleteTask = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "All Tasks Completed"
)
@Composable
private fun PlannerHomeCompletedPreview() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                greeting = "Hello, Student!",
                totalTasks = 7,
                completedTasks = 7
            ),
            onCompleteTask = {}
        )
    }
}

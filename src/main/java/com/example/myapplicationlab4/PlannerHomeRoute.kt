
package com.example.myapplicationlab4

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myapplicationlab4.ui.PlannerHomeScreen
import com.example.myapplicationlab4.ui.PlannerUiState

@Composable
fun PlannerHomeRoute() {

    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                greeting = "Hello, Student!",
                totalTasks = 7,
                completedTasks = 0
            )
        )
    }

    PlannerHomeScreen(
        uiState = uiState,
        onCompleteTask = {
            if (uiState.completedTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    completedTasks = uiState.completedTasks + 1
                )
            }
        }
    )
}

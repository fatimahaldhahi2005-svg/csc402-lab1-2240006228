package com.example.myapplicationlab2


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TaskItem(
    val id: Int,
    val title: String,
    val priority: String,
    var isCompleted: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTrackerScreen(modifier: Modifier = Modifier) {
    var taskTitle by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("Medium") }
    var expanded by remember { mutableStateOf(false) }

    val priorities = listOf("Low", "Medium", "High")
    val taskList = remember { mutableStateListOf<TaskItem>() }
    var nextId by remember { mutableStateOf(1) }

    val completedCount = taskList.count { it.isCompleted }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Task Tracker",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // بطاقة الإحصائيات (Summary Card)
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total Tasks: ${taskList.size}", fontWeight = FontWeight.Medium)
                Text(text = "Completed: $completedCount", fontWeight = FontWeight.Medium)
            }
        }

        // حقل إدخال اسم المهمة
        OutlinedTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            label = { Text("Task Title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // القائمة المنسدلة لاختيار الأولوية
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedPriority,
                onValueChange = {},
                readOnly = true,
                label = { Text("Priority") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                priorities.forEach { priority ->
                    DropdownMenuItem(
                        text = { Text(priority) },
                        onClick = {
                            selectedPriority = priority
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // زر إضافة المهمة
        Button(
            onClick = {
                if (taskTitle.isNotBlank()) {
                    taskList.add(
                        TaskItem(
                            id = nextId++,
                            title = taskTitle.trim(),
                            priority = selectedPriority
                        )
                    )
                    taskTitle = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Task", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // قائمة عرض المهام (LazyColumn)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(taskList, key = { it.id }) { task ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = task.title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Priority: ${task.priority}",
                                fontSize = 12.sp,
                                color = when (task.priority) {
                                    "High" -> MaterialTheme.colorScheme.error
                                    "Medium" -> MaterialTheme.colorScheme.tertiary
                                    else -> MaterialTheme.colorScheme.secondary
                                }
                            )
                        }

                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = { checked ->
                                val index = taskList.indexOf(task)
                                if (index != -1) {
                                    taskList[index] = task.copy(isCompleted = checked)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
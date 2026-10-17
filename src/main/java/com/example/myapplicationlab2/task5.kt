package com.example.myapplicationlab2

data class Course(val code: String, val name: String, val credits: Int)

fun main() {
    println("--- Task 5: Data Classes ---")

    val course1 = Course("CSC 402", "Mobile Application Programming", 3)
    val course2 = Course("CSC 301", "Database Systems", 3)

    val courses = listOf(course1, course2)

    for (course in courses) {
        println("Course Code: ${course.code} | Name: ${course.name} | Credits: ${course.credits}")
    }
}
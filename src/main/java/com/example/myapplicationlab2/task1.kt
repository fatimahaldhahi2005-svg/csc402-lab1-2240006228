package com.example.myapplicationlab2

fun main() {
    println("--- Task 3: Null Safety Check ---")

    val optionalName: String? = "Mobile App Programming"
    val emptyName: String? = null

    // استخدام Safe call (?.) والـ Elvis operator (?:)
    println("Length of optionalName: ${optionalName?.length ?: 0}")
    println("Length of emptyName: ${emptyName?.length ?: "Name is missing"}")

    optionalName?.let {
        println("Course name in upper case: ${it.uppercase()}")
    }
}
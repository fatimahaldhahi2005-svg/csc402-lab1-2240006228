package com.example.myapplicationlab2

fun main() {
    println("--- Task 2: Grade Evaluation ---")
    val score = 88

    val grade = when (score) {
        in 90..100 -> "A+"
        in 85..89  -> "A"
        in 80..84  -> "B+"
        in 75..79  -> "B"
        in 70..74  -> "C+"
        in 65..69  -> "C"
        in 60..64  -> "D+"
        in 50..59  -> "D"
        else       -> "F"
    }

    println("Score: $score, Grade: $grade")
}
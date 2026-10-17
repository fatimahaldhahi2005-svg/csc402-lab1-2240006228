package com.example.myapplicationlab2

class task4 {
}

fun main() {
    println("--- Task 4: Collections ---")

    val scores = listOf(85, 92, 67, 45, 78, 90, 88)

    val passingScores = scores.filter { it >= 60 }
    val averageScore = scores.average()

    println("All Scores: $scores")
    println("Passing Scores (>=60): $passingScores")
    println("Average Score: %.2f".format(averageScore))
}
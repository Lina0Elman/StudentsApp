package com.example.studentsapp

data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean = false,
    var picture: Int // Reference to drawable resource
)
package com.example.studentsapp.model

data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean = false,
    var picture: Int // Resource ID for the student's picture
)

package com.example.studentsapp.repository

import com.example.studentsapp.R
import com.example.studentsapp.model.Student

object StudentRepository {
    private val students = mutableListOf<Student>()

    init {
        // Default student data
        students.add(Student("1", "Lina", false, R.drawable.student_pic))
    }

    fun getStudents(): MutableList<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(index: Int, updatedStudent: Student) {
        if (index in students.indices) {
            students[index] = updatedStudent
        }
    }

    fun deleteStudent(index: Int) {
        if (index in students.indices) {
            students.removeAt(index)
        }
    }
}

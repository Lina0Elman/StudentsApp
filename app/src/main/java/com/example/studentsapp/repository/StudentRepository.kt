package com.example.studentsapp.repository

import com.example.studentsapp.model.Student
import com.example.studentsapp.R

object StudentRepository {
    private val students = mutableListOf<Student>()

    init {
        // Add a default student for testing
        students.add(Student("1", "Lina", false, R.drawable.student_pic))
    }

    fun getStudents(): List<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(index: Int, updatedStudent: Student) {
        students[index] = updatedStudent
    }

    fun deleteStudent(index: Int) {
        students.removeAt(index)
    }
}

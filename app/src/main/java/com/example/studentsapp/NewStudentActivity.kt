package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Student
import com.example.studentsapp.repository.StudentRepository

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val inputName = findViewById<EditText>(R.id.inputName)
        val inputId = findViewById<EditText>(R.id.inputId)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val name = inputName.text.toString()
            val id = inputId.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                StudentRepository.addStudent(Student(id, name, false, R.drawable.student_pic))
                Toast.makeText(this, "Student added!", Toast.LENGTH_SHORT).show()
                finish() // Go back to the previous screen
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

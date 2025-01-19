package com.example.studentsapp.activities

import Student
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R

class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val id = findViewById<EditText>(R.id.studentIdInput).text.toString()
            val name = findViewById<EditText>(R.id.studentNameInput).text.toString()

            if (id.isNotEmpty() && name.isNotEmpty()) {
                StudentsRepository.add(Student(id, name))
                finish()
            }
        }
    }
}
package com.example.studentsapp.activities

import Student
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R



class EditStudentActivity : AppCompatActivity() {
    private var originalId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        originalId = intent.getStringExtra("student_id")
        val student = originalId?.let { StudentsRepository.getById(it) }

        student?.let {
            findViewById<EditText>(R.id.studentIdInput).setText(it.id)
            findViewById<EditText>(R.id.studentNameInput).setText(it.name)
        }

        findViewById<Button>(R.id.updateButton).setOnClickListener {
            val newId = findViewById<EditText>(R.id.studentIdInput).text.toString()
            val newName = findViewById<EditText>(R.id.studentNameInput).text.toString()

            if (newId.isNotEmpty() && newName.isNotEmpty()) {
                student?.let {
                    val updatedStudent = Student(newId, newName, it.isChecked)
                    StudentsRepository.update(updatedStudent)
                }
                finish()
            }
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            originalId?.let {
                StudentsRepository.delete(it)
                finish()
            }
        }
    }
}
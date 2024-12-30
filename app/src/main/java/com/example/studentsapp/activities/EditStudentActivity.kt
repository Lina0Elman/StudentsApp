package com.example.studentsapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.model.Student
import com.example.studentsapp.repository.StudentRepository

class EditStudentActivity : AppCompatActivity() {
    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val editName = findViewById<EditText>(R.id.editName)
        val editId = findViewById<EditText>(R.id.editId)
        val updateButton = findViewById<Button>(R.id.updateButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        studentIndex = intent.getIntExtra("STUDENT_INDEX", -1)

        if (studentIndex != -1) {
            val student = StudentRepository.getStudents()[studentIndex]
            editName.setText(student.name)
            editId.setText(student.id)

            updateButton.setOnClickListener {
                val updatedName = editName.text.toString()
                val updatedId = editId.text.toString()

                if (updatedName.isNotEmpty() && updatedId.isNotEmpty()) {
                    StudentRepository.updateStudent(
                        studentIndex,
                        student.copy(name = updatedName, id = updatedId)
                    )
                    Toast.makeText(this, "Student updated!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
                }
            }

            deleteButton.setOnClickListener {
                StudentRepository.deleteStudent(studentIndex)
                Toast.makeText(this, "Student deleted!", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            Toast.makeText(this, "Student not found!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

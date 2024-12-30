package com.example.studentsapp.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val student = intent.getSerializableExtra("student") as Student

        val studentImage = findViewById<ImageView>(R.id.studentDetailsImage)
        val studentName = findViewById<TextView>(R.id.studentDetailsName)
        val studentId = findViewById<TextView>(R.id.studentDetailsId)

        studentImage.setImageResource(student.picture)
        studentName.text = "Name: ${student.name}"
        studentId.text = "ID: ${student.id}"
    }
}

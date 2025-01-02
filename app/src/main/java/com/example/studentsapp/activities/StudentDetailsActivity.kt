package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
//import com.example.studentsapp.model.StudentsRepository

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val studentId = intent.getStringExtra("student_id")
        val student = studentId?.let { StudentsRepository.getById(it) }

        student?.let {
            findViewById<ImageView>(R.id.studentImage).setImageResource(R.drawable.student_pic)
            findViewById<TextView>(R.id.studentName).text = it.name
            findViewById<TextView>(R.id.studentId).text = it.id
            findViewById<TextView>(R.id.studentStatus).text =
                if (it.isChecked) "Checked" else "Not checked"
        }

        findViewById<Button>(R.id.editButton).setOnClickListener {
            Intent(this, EditStudentActivity::class.java).apply {
                putExtra("student_id", studentId)
                startActivity(this)
            }
        }
    }
}
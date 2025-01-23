package com.example.studentsapp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R

class StudentDetailsActivity : AppCompatActivity() {
    private var studentId: String? = null
    private val REQUEST_CODE_EDIT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentId = intent.getStringExtra("student_id")

        findViewById<Button>(R.id.editButton).setOnClickListener {
            Intent(this, EditStudentActivity::class.java).apply {
                putExtra("student_id", studentId)
                startActivityForResult(this, REQUEST_CODE_EDIT)
            }
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            studentId?.let {
                StudentsRepository.delete(it)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateStudentDetails()
    }

    private fun updateStudentDetails() {
        val student = studentId?.let { StudentsRepository.getById(it) }

        student?.let {
            findViewById<ImageView>(R.id.studentImage).setImageResource(R.drawable.student_pic)
            findViewById<TextView>(R.id.studentName).text = "Name: ${it.name}"
            findViewById<TextView>(R.id.studentId).text = "ID: ${it.id}"
            findViewById<TextView>(R.id.studentPhone).text = "Phone: ${it.phoneNumber}"
            findViewById<TextView>(R.id.studentAddress).text = "Address: ${it.address}"
            findViewById<TextView>(R.id.studentStatus).text =
                "Status: ${if (it.isChecked) "Checked" else "Not checked"}"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK) {
            studentId = data?.getStringExtra("updated_student_id")
            updateStudentDetails()
        }
    }
}
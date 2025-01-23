package com.example.studentsapp.activities

import StudentsAdapter
import StudentsRepository
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: StudentsAdapter
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.studentsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentsAdapter(
            StudentsRepository.getAll(),
            onItemClick = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                    putExtra("student_id", student.id)
                }
                startActivityForResult(intent, REQUEST_CODE)
            },
            onCheckChanged = { student ->
                StudentsRepository.toggleCheck(student.id)
                adapter.updateStudents(StudentsRepository.getAll())
            },
            onIdChanged = { studentId ->
                val updatedStudents = StudentsRepository.getAll()
                adapter.updateStudents(updatedStudents)
                val position = updatedStudents.indexOfFirst { it.id == studentId }
                if (position != -1) {
                    adapter.notifyItemChanged(position)
                }
            }
        )
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.addStudentFab).setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.updateStudents(StudentsRepository.getAll())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val updatedStudentId = data?.getStringExtra("updated_student_id")
            updatedStudentId?.let { studentId ->
                val updatedStudents = StudentsRepository.getAll()
                adapter.updateStudents(updatedStudents)
                val position = updatedStudents.indexOfFirst { it.id == studentId }
                if (position != -1) {
                    adapter.notifyItemChanged(position)
                }
            }
        }
    }
}
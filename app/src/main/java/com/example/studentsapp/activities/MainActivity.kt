package com.example.studentsapp.activities

import StudentsAdapter
import StudentsRepository
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.studentsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentsAdapter(
            StudentsRepository.getAll(),
            onItemClick = { student ->
                Intent(this, StudentDetailsActivity::class.java).apply {
                    putExtra("student_id", student.id)
                    startActivity(this)
                }
            },
            onCheckChanged = { student ->
                StudentsRepository.toggleCheck(student.id)
                adapter.updateStudents(StudentsRepository.getAll())
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
}
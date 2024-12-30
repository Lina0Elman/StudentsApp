package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.activities.NewStudentActivity
import com.example.studentsapp.activities.StudentDetailsActivity
import com.example.studentsapp.adapter.StudentAdapter
import com.example.studentsapp.repository.StudentRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val students = StudentRepository.getStudents()
        Log.d("MainActivity", "Students: $students") // Check if students list is populated

        adapter = StudentAdapter(students) { student, position ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_INDEX", position)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        val fab: FloatingActionButton = findViewById(R.id.fab_add_student)
        fab.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.updateStudents(StudentRepository.getStudents())
    }
}
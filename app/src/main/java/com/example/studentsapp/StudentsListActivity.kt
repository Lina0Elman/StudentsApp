package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.repository.StudentRepository

class StudentsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = StudentAdapter(StudentRepository.getStudents()) { student, index ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("studentIndex", index)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}

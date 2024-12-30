package com.example.studentsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

class StudentAdapter(
    private val students: MutableList<Student>,
    private val onItemClicked: (Student, Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.name.text = student.name
        holder.id.text = student.id
        holder.picture.setImageResource(student.picture)

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = student.isChecked
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        holder.itemView.setOnClickListener {
            onItemClicked(student, position)
        }
    }

    override fun getItemCount(): Int = students.size

    fun updateStudents(newStudents: List<Student>) {
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()
    }

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.studentName)
        val id: TextView = view.findViewById(R.id.studentId)
        val picture: ImageView = view.findViewById(R.id.studentImage)
        val checkBox: CheckBox = view.findViewById(R.id.studentCheckBox)
    }
}

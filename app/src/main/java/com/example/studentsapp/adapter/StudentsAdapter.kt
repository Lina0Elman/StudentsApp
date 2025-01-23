import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R

class StudentsAdapter(
    private var students: List<Student>,
    private val onItemClick: (Student) -> Unit,
    private val onCheckChanged: (Student) -> Unit,
    private val onIdChanged: (studentId: String) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.studentImage)
        val nameText: TextView = view.findViewById(R.id.studentName)
        val idText: TextView = view.findViewById(R.id.studentId)
        val checkBox: CheckBox = view.findViewById(R.id.studentCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameText.text = student.name
        holder.idText.text = student.id
        holder.checkBox.isChecked = student.isChecked
        holder.imageView.setImageResource(R.drawable.student_pic)

        holder.itemView.setOnClickListener { onItemClick(student) }
        holder.idText.doOnTextChanged { text, _, _, _ ->
            onIdChanged(text.toString())
        }
        holder.checkBox.setOnClickListener { onCheckChanged(student) }
    }

    override fun getItemCount() = students.size

    fun updateStudents(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}
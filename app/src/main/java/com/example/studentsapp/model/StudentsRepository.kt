object StudentsRepository {
    private val students = mutableListOf<Student>()

    fun getAll(): List<Student> = students.toList()

    fun getById(id: String): Student? = students.find { it.id == id }

    fun add(student: Student) {
        students.add(student)
    }

    fun update(student: Student, originalId: String?) {
        val index = students.indexOfFirst { it.id == originalId }
        if (index != -1) {
            students[index] = student
        }
    }

    fun delete(id: String) {
        students.removeIf { it.id == id }
    }

    fun toggleCheck(id: String) {
        val student = getById(id)
        student?.let {
            it.isChecked = !it.isChecked
        }
    }
}
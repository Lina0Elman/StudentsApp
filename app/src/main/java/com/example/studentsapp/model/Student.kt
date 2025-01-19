data class Student(
    var id: String,
    var name: String,
    var isChecked: Boolean = false,
    var imageUrl: String = "default_student",
    var phoneNumber: String = "",
    var address: String = ""
)
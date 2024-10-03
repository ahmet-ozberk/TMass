package tosbik.ao.tmass.domain.model

data class HomeTaskModel(
    val id: Int = 0,
    val category: String = "Development",
    val priority: String = "High",
    val title: String = "Create a Settings Screen",
    val project: String = "Tmass",
    val dueDate: String = "Mon, 15 feb 2024",
    val status: String = "In Progress",
)

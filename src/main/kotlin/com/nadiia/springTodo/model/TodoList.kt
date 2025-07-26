package com.nadiia.springTodo.model

data class TodoList(var name: String){
    // TODO possibility to have more than 1 list :)
    private var _id: Int = 0
    val id: Int
        get() = _id

    private var _tasks: MutableList<Task> = mutableListOf()

    fun getTasks() = _tasks
    fun addTask(task: Task) {
        _tasks.add(task)
    }
    fun deleteTask(id: Int) {
        _tasks.find { it.id == id }?.apply { _tasks.remove(this) }
            ?:throw NoSuchElementException("Task with id $id not found")
    }
}

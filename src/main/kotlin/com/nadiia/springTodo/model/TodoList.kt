package com.nadiia.springTodo.model

data class TodoList(var name: String){
    // TODO possibility to have more than 1 list :)
    private var _id: Int = 0
    val id: Int
        get() = _id

    private var _tasks: MutableList<Task> = mutableListOf()

    init {
        println("List created: [$_id]: $name")
    }

    fun getTasks() = _tasks
    fun addTask(task: Task) {
        _tasks.add(task)
        println("Task added: [${task.id}]: ${task.task}")
    }
    fun deleteTask(id: Int) {
        if (id < _tasks.size) {
            _tasks.removeAt(id)
        } else println("Task with id $id doesn't exist")
    }
    fun markDone(id: Int) {
        if (id < _tasks.size) {
            _tasks[id].markDone()
        } else println("Task with id $id doesn't exist")
    }
}

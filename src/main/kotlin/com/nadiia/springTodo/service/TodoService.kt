package com.nadiia.springTodo.service

import com.nadiia.springTodo.model.Task
import com.nadiia.springTodo.model.TodoList
import org.springframework.stereotype.Service

@Service
class TodoService {
    private var list = TodoList("My first list")
    init {
        list.addTask(Task(0, "My first task"))
        list.addTask(Task(1, "My another task"))
        list.addTask(Task(3, "My the other task"))
        list.getTasks().first().markDone()
    }

    fun getTasks() : List<Task> = list.getTasks().sortedBy { it.id }

    private fun findAvailableId() : Int {
        val sortedIds = list.getTasks().map { it.id }.sorted()
        return(0..sortedIds.size).first{it !in sortedIds}
    }

    fun addToList(task: String){
        if (task.isEmpty()) throw IllegalArgumentException("Task name is empty")
        list.addTask(Task(findAvailableId(), task))
    }

    private fun getTaskById(id: Int) : Task? = list.getTasks().find { it.id == id}

    fun markDone(id: Int){
        val toMark: Task? = getTaskById(id)
        if (toMark != null) {
            toMark.markDone()
        } else throw NoSuchElementException("Task with id $id not found")
    }

    fun deleteTask(id: Int) {
        if (id < 0 || id >= list.getTasks().size) throw NoSuchElementException("Task with id $id not found")
        list.deleteTask(id)
    }
}

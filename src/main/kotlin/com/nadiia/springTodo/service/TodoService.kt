package com.nadiia.springTodo.service

import com.nadiia.springTodo.model.Task
import com.nadiia.springTodo.model.TodoList
import org.springframework.stereotype.Service

@Service
class TodoService {
    fun printTasks(todoList: TodoList){
        val list = todoList.getTasks()
        println(todoList.name)
        list.sortBy{it.id}
        for (task in list) {
            println("[${task.id}]:[${if(task.isDone())"Done" else "TODO"}]:  ${task.task} ")
        }
    }

    fun findAvailableId(list : TodoList) : Int {
        val sortedIds = list.getTasks().map { it.id }.sorted()
        return(0..sortedIds.size).first{it !in sortedIds}
    }

    fun addToList(list: TodoList){
        println("Write your new task:")
        val input = readlnOrNull()
        val task = input ?: ""
        if (task.isNotEmpty()) {
            list.addTask(Task(findAvailableId(list), task))
        } else println("Error adding new task")
    }

    fun markDone(list: TodoList){
        println("Write id of the task:")
        val input: Int? = readlnOrNull()?.toIntOrNull()
        val id: Int = input ?: -1
        if (id < 0 || id >= list.getTasks().size) println("Error: ID $id is invalid")
        else list.markDone(id)
    }

    fun deleteTask(list: TodoList) {
        println("Write id of the task:")
        val input: Int? = readlnOrNull()?.toIntOrNull()
        val id: Int = input ?: -1
        if (id < 0 || id >= list.getTasks().size) println("Error: ID $id is invalid")
        else list.deleteTask(id)
    }
}

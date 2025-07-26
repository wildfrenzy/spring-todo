package com.nadiia.springTodo.controller

import com.nadiia.springTodo.model.Task
import com.nadiia.springTodo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/")
class TodoController(private val todoService: TodoService) {

    @GetMapping("/api/tasks")
    fun tasks() = todoService.getTasks()

    @PostMapping("/api/task")
    fun addTask(@RequestBody taskData: Task) : Task{
        todoService.addToList(taskData.task)
        return taskData
    }

    @DeleteMapping("/api/task/{id}")
    fun deleteTask(@PathVariable id: Int): ResponseEntity<Void> {
        todoService.deleteTask(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/api/task/{id}")
    fun markDone(@PathVariable id: Int): ResponseEntity<Void>  {
        todoService.markDone(id)
        return ResponseEntity.noContent().build()
    }
}

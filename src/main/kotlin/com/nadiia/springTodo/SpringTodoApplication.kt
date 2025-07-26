package com.nadiia.springTodo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringTodoApplication

fun main(args: Array<String>) {
	runApplication<SpringTodoApplication>(*args)
}

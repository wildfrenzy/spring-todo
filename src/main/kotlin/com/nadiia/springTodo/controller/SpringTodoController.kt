package com.nadiia.springTodo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/")
class SpringTodoController {
    @GetMapping("/")
    fun hello() = "Hello World!"
}

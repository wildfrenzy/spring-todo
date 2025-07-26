package com.nadiia.springTodo.model

data class Task(val id: Int, var task: String){
    private var _done: Boolean = false
    fun isDone(): Boolean = _done
    fun markDone() { _done = true }
}

package com.example.todomvc.controller;

import com.example.todomvc.model.TodoItem;
import com.example.todomvc.service.TodoService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public String addItem(@RequestBody String itemText) {
        todoService.addItem(itemText);
        return "sucess!";
    }

    @GetMapping(value = "/todoItems")
    public List<TodoItem> printTodoItems() {
        return todoService.listAllTodos();
    }
}

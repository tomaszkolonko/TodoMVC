package com.example.todomvc.service;

import com.example.todomvc.repository.TodoRepository;
import com.example.todomvc.model.TodoItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void addItem(String itemText) {
        TodoItem todoItem = new TodoItem(itemText, false);
        todoRepository.save(todoItem);
    }

    public List<TodoItem> listAllTodos() {
        return todoRepository.findAll();
    }
}

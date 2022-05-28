package com.example.todomvc.model;

import com.example.todomvc.controller.TodoController;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private boolean checked;

    public TodoItem() {
    }

    public TodoItem(String description, boolean checked) {
        this.description = description;
        this.checked = checked;
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof TodoItem && this.equals((TodoItem) other);
    }

    private boolean equals(final TodoItem other) {
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, checked);
    }
}

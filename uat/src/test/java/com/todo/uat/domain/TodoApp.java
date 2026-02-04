package com.todo.uat.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Minimal in-memory model of a todo list used only for basic unit testing.
 */
public class TodoApp {

    private final List<String> todos;

    public TodoApp() {
        this.todos = new ArrayList<>();
    }

    TodoApp(List<String> initialTodos) {
        this.todos = new ArrayList<>(initialTodos);
    }

    public void addTodo(String description) {
        Objects.requireNonNull(description, "Todo description cannot be null");
        todos.add(description);
    }

    public boolean containsTodo(String description) {
        return todos.contains(description);
    }

    public boolean deleteTodo(String description) {
        return todos.remove(description);
    }

    public int size() {
        return todos.size();
    }

    public void clear() {
        todos.clear();
    }

    public List<String> snapshot() {
        return Collections.unmodifiableList(new ArrayList<>(todos));
    }

    public static TodoApp fromSnapshot(List<String> snapshot) {
        return new TodoApp(snapshot);
    }
}

package com.todo.uat.abilities;

import com.todo.uat.domain.TodoApp;
import com.todo.uat.screenplay.Ability;

import java.util.Objects;

public final class TodoAppUser implements Ability {

    private final TodoApp todoApp;
    private final String baseUrl;

    private TodoAppUser(TodoApp todoApp, String baseUrl) {
        this.todoApp = Objects.requireNonNull(todoApp);
        this.baseUrl = Objects.requireNonNull(baseUrl);
    }

    public static TodoAppUser browsingTheTodoAppAt(String baseUrl) {
        Objects.requireNonNull(baseUrl);
        return new TodoAppUser(new TodoApp(), baseUrl);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public TodoApp getTodoApp() {
        return todoApp;
    }

    public void open() {
        todoApp.clear();
    }

    public void addTodo(String description) {
        todoApp.addTodo(description);
    }

    public boolean contains(String description) {
        return todoApp.containsTodo(description);
    }

    public boolean delete(String description) {
        return todoApp.deleteTodo(description);
    }

    public void closeBrowser() {
        todoApp.clear();
    }
}

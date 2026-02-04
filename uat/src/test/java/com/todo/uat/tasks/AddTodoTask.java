package com.todo.uat.tasks;

import com.todo.uat.abilities.TodoAppUser;
import com.todo.uat.screenplay.Task;

import java.util.Objects;

public final class AddTodoTask {

    private AddTodoTask() {
        // helper
    }

    public static Task withDescription(String description) {
        Objects.requireNonNull(description);
        return Task.where("Add the todo item '" + description + "'", actor -> {
            TodoAppUser ability = actor.abilityTo(TodoAppUser.class);
            ability.addTodo(description);
        });
    }
}

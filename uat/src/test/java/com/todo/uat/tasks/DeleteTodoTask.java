package com.todo.uat.tasks;

import com.todo.uat.abilities.TodoAppUser;
import com.todo.uat.screenplay.Task;

import java.util.Objects;

public final class DeleteTodoTask {

    private DeleteTodoTask() {
        // helper
    }

    public static Task theTodoContaining(String description) {
        Objects.requireNonNull(description);
        return Task.where("Delete the todo item '" + description + "'", actor -> {
            TodoAppUser ability = actor.abilityTo(TodoAppUser.class);
            ability.delete(description);
        });
    }
}

package com.todo.uat.tasks;

import com.todo.uat.abilities.TodoAppUser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public final class DeleteTodoTask {

    private DeleteTodoTask() {
        // helper
    }

    public static Task theTodoContaining(String description) {
        return Task.where("Delete the todo item '#description'", actor -> {
            TodoAppUser ability = actor.abilityTo(TodoAppUser.class);
            ability.getTodoApp().deleteTodo(description);
        });
    }
}

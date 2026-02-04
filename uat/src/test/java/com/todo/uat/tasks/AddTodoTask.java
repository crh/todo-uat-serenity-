package com.todo.uat.tasks;

import com.todo.uat.abilities.TodoAppUser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public final class AddTodoTask {

    private AddTodoTask() {
        // helper
    }

    public static Task withDescription(String description) {
        return Task.where("Add the todo item '#description'", actor -> {
            TodoAppUser ability = actor.abilityTo(TodoAppUser.class);
            ability.getTodoApp().addTodo(description);
        });
    }
}

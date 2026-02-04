package com.todo.uat.tasks;

import com.todo.uat.abilities.TodoAppUser;
import com.todo.uat.screenplay.Task;

public final class OpenTodoApp {

    private OpenTodoApp() {
        // helper
    }

    public static Task page() {
        return Task.where("Open the Todo application", actor -> {
            TodoAppUser ability = actor.abilityTo(TodoAppUser.class);
            ability.open();
        });
    }
}

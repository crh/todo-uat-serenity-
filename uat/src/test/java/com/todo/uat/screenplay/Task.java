package com.todo.uat.screenplay;

import java.util.Objects;

public final class Task implements Performable {

    private final String description;
    private final Performable action;

    private Task(String description, Performable action) {
        this.description = Objects.requireNonNull(description);
        this.action = Objects.requireNonNull(action);
    }

    @Override
    public void performAs(Actor actor) {
        action.performAs(actor);
    }

    public static Task where(String description, Performable action) {
        return new Task(description, action);
    }

    @Override
    public String toString() {
        return description;
    }
}

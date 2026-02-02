package com.todo.uat.actors;

import net.serenitybdd.screenplay.Actor;

public class TodoActor {

    public static Actor named(String name) {
        return Actor.named(name).describedAs("a todo application user");
    }

    private TodoActor() {
        // Private constructor to prevent instantiation
    }
}

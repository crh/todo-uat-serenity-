package com.todo.uat.actors;

import net.serenitybdd.screenplay.Actor;

public final class TodoActor {

    private TodoActor() {
        // utility class
    }

    public static Actor named(String name) {
        return Actor.named(name);
    }
}

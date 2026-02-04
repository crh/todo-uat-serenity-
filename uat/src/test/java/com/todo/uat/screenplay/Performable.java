package com.todo.uat.screenplay;

/**
 * Represents something an actor can do.
 */
@FunctionalInterface
public interface Performable {
    void performAs(Actor actor);
}

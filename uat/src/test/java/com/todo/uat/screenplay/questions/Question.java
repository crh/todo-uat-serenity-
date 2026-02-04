package com.todo.uat.screenplay.questions;

import com.todo.uat.screenplay.Actor;

import java.util.Objects;
import java.util.function.Function;

public final class Question<T> {

    private final String description;
    private final Function<Actor, T> resolver;

    private Question(String description, Function<Actor, T> resolver) {
        this.description = Objects.requireNonNull(description);
        this.resolver = Objects.requireNonNull(resolver);
    }

    public static <T> Question<T> about(String description, Function<Actor, T> resolver) {
        return new Question<>(description, resolver);
    }

    public T answeredBy(Actor actor) {
        return resolver.apply(actor);
    }

    @Override
    public String toString() {
        return description;
    }
}

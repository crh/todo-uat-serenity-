package com.todo.uat.screenplay;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Actor {

    private final String name;
    private final Map<Class<? extends Ability>, Ability> abilities = new ConcurrentHashMap<>();

    private Actor(String name) {
        this.name = Objects.requireNonNull(name).trim();
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    public Actor can(Ability ability) {
        abilities.put(ability.getClass(), ability);
        return this;
    }

    public <T extends Ability> T abilityTo(Class<T> abilityClass) {
        Ability ability = abilities.get(abilityClass);
        if (ability == null) {
            throw new IllegalStateException(name + " does not have ability " + abilityClass.getSimpleName());
        }
        return abilityClass.cast(ability);
    }

    public void attemptsTo(Performable... performables) {
        for (Performable performable : performables) {
            performable.performAs(this);
        }
    }

    public String getName() {
        return name;
    }
}

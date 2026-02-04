package com.todo.uat.questions;

import com.todo.uat.abilities.TodoAppUser;
import com.todo.uat.screenplay.questions.Question;

import java.util.Objects;

public final class TodoQuestions {

    private TodoQuestions() {
        // helper class
    }

    public static Question<Boolean> todoItemWithTextExists(String text) {
        Objects.requireNonNull(text);
        return Question.about("todo item with text '" + text + "' exists", actor -> {
            TodoAppUser ability = actor.abilityTo(TodoAppUser.class);
            return ability.contains(text);
        });
    }
}

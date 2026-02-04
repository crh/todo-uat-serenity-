package com.todo.uat.questions;

import com.todo.uat.abilities.TodoAppUser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Question;

public final class TodoQuestions {

    private TodoQuestions() {
        // helper class
    }

    public static Question<Boolean> todoItemWithTextExists(String text) {
        return Question.about("todo item with text '" + text + "' exists", actor -> {
            TodoAppUser ability = actor.abilityTo(TodoAppUser.class);
            return ability.getTodoApp().containsTodo(text);
        });
    }
}

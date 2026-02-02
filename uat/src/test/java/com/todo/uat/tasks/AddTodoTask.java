package com.todo.uat.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.todo.uat.interactions.Locators;

public class AddTodoTask {

    public static Task withDescription(String description) {
        return Task.where("{0} adds a todo with description '#description'",
                actor -> actor.attemptsTo(
                        WaitUntil.the(Locators.NEW_TODO_INPUT, isVisible()).forNoLongerThan(5).seconds(),
                        Enter.theValue(description).into(Locators.NEW_TODO_INPUT),
                        Click.on(Locators.ADD_TODO_BUTTON)
                )
        );
    }

    private AddTodoTask() {
        // Private constructor
    }
}

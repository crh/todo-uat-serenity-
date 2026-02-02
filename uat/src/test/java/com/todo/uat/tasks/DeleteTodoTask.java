package com.todo.uat.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.todo.uat.interactions.Locators;

public class DeleteTodoTask {

    public static Task theTodoContaining(String text) {
        return Task.where("{0} deletes the todo containing '#text'",
                actor -> actor.attemptsTo(
                        // Wait for the specific todo item to be visible before trying to delete it
                        WaitUntil.the(Locators.todoItemContainingText(text), isVisible()).forNoLongerThan(5).seconds(),
                        Click.on(Locators.deleteButtonForTodoContainingText(text))
                )
        );
    }

    private DeleteTodoTask() {
        // Private constructor
    }
}

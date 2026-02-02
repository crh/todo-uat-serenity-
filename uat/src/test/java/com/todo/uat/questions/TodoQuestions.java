package com.todo.uat.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

public class TodoQuestions {

    public static Question<Boolean> todoItemWithTextExists(String text) {
        return Question.about("whether a todo item with text '#text' exists", actor -> {
            Target todoItem = Target.the("todo item containing text " + text).locatedBy(com.todo.uat.interactions.Locators.todoItemContainingText(text));
            return todoItem.resolveFor(actor).isPresent();
        });
    }

    public static Question<Integer> numberOfTodoItems() {
        return Question.about("the number of todo items", actor -> {
            // This question will count all the elements that match the 'todo-item' data-testid.
            // It assumes that each `<li>` element with `data-testid='todo-item'` represents one todo.
            Target todoItems = Target.the("all todo items").locatedBy(com.todo.uat.interactions.Locators.TODO_ITEM);
            List<?> elements = todoItems.resolveFor(actor).findElements();
            return elements.size();
        });
    }

    // This question can be used to get the text of a specific todo item if needed, e.g. for verification.
    // However, for simplicity and directness, `todoItemWithTextExists` is often sufficient.
    public static Question<String> textOfTodoItemContaining(String text) {
        return Question.about("the text of the todo item containing '#text'", actor -> {
            Target todoItem = Target.the("todo item containing text " + text).locatedBy(com.todo.uat.interactions.Locators.todoItemContainingText(text));
            // Get the text of the span element within the found todo item.
            return Attribute.of("text").of(todoItem.then(Target.the("the text span").located(By.tagName("span")))).answeredBy(actor);
        });
    }

    private TodoQuestions() {
        // Private constructor
    }
}
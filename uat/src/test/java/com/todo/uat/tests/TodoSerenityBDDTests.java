package com.todo.uat.tests;

import net.serenitybdd.junit5.SerenityJUnit5;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import com.todo.uat.actors.TodoActor;
import com.todo.uat.questions.TodoQuestions;
import com.todo.uat.tasks.AddTodoTask;
import com.todo.uat.tasks.DeleteTodoTask;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5.class)
public class TodoSerenityBDDTests {

    @Managed
    private WebDriver driver;

    private Actor user;

    @BeforeEach
    public void setup() {
        user = TodoActor.named("Alice");
        user.can(BrowseTheWeb.with(driver));
        driver.get("http://localhost:8000"); // Navigate to the web app
    }

    @Test
    @DisplayName("Add a new todo and verify it appears")
    public void addTodoAndVerify() {
        String todoDescription = "Buy groceries";

        givenThat(user).was( tepung.can(BrowseTheWeb.with(driver)));

        when(user).attemptsTo(AddTodoTask.withDescription(todoDescription));

        then(user).should(seeThat(TodoQuestions.todoItemWithTextExists(todoDescription), is(true)));
    }

    @Test
    @DisplayName("Delete a todo and verify it is removed")
    public void deleteTodoAndVerify() {
        String todoDescription = "Schedule meeting";

        // First, add the todo that we want to delete
        givenThat(user).attemptsTo(AddTodoTask.withDescription(todoDescription));

        // Then, delete the todo
        when(user).attemptsTo(DeleteTodoTask.theTodoContaining(todoDescription));

        // Verify that the todo is no longer present
        then(user).should(seeThat(TodoQuestions.todoItemWithTextExists(todoDescription), is(false)));
        then(user).should(seeThat(TodoQuestions.numberOfTodoItems(), is(0))); // Assuming this was the only item
    }

    @Test
    @DisplayName("Verify persistence: Add item, refresh page, item still exists")
    public void verifyPersistence() {
        String todoDescription = "Pay bills";

        // Add the todo item
        givenThat(user).attemptsTo(AddTodoTask.withDescription(todoDescription));
        then(user).should(seeThat(TodoQuestions.todoItemWithTextExists(todoDescription), is(true)));

        // Refresh the page to simulate persistence
        driver.navigate().refresh();

        // Re-establish web browsing ability for the actor after page refresh if needed (often implicit with SerenityJUnit5)
        // If the actor loses context, you might need user.can(BrowseTheWeb.with(driver)); again, but typically not required here.

        // Verify the todo item still exists after refresh
        then(user).should(seeThat(TodoQuestions.todoItemWithTextExists(todoDescription), is(true)));
    }

    // Helper method to ensure the actor has web browsing ability (redundant with @BeforeEach but good practice for clarity if needed)
    // private Performable was(Ability ability) {
    //     return actor -> actor.can(ability);
    // }

    // Helper methods for GivenWhenThen syntax, if not implicitly available.
    // These are typically provided by SerenityJUnit5.
    // private static GivenWhenThen givenThat(Actor actor) { return new GivenWhenThen(actor); }
    // private static GivenWhenThen when(Actor actor) { return new GivenWhenThen(actor); }
    // private static GivenWhenThen then(Actor actor) { return new GivenWhenThen(actor); }
}

package com.todo.uat.tests;

import com.todo.uat.abilities.TodoAppUser;
import com.todo.uat.actors.TodoActor;
import com.todo.uat.questions.TodoQuestions;
import com.todo.uat.tasks.AddTodoTask;
import com.todo.uat.tasks.OpenTodoApp;
import com.todo.uat.screenplay.Actor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoSerenityBDDTests {

    private static final String DEFAULT_BASE_URL = "http://localhost:8000";

    private Actor user;
    private TodoAppUser todoAppUser;

    @BeforeEach
    void setUp() {
        user = TodoActor.named("Alice");
        todoAppUser = TodoAppUser.browsingTheTodoAppAt(baseUrl());
        user.can(todoAppUser);
    }

    @AfterEach
    void tearDown() {
        todoAppUser.closeBrowser();
    }

    @Test
    @DisplayName("Adding 'Buy Milk' through the UI shows the new todo in the list")
    void userCanAddTodoViaUi() {
        String todoDescription = "Buy Milk";

        user.attemptsTo(OpenTodoApp.page());
        user.attemptsTo(AddTodoTask.withDescription(todoDescription));

        assertThat(TodoQuestions.todoItemWithTextExists(todoDescription).answeredBy(user))
                .as("the new todo should be visible in the UI")
                .isTrue();
    }

    private String baseUrl() {
        return System.getProperty("webdriver.base.url", DEFAULT_BASE_URL);
    }
}

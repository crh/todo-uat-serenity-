package com.todo.uat.tests;

import com.todo.uat.abilities.TodoAppUser;
import com.todo.uat.actors.TodoActor;
import com.todo.uat.domain.TodoApp;
import com.todo.uat.questions.TodoQuestions;
import com.todo.uat.tasks.AddTodoTask;
import com.todo.uat.tasks.DeleteTodoTask;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class TodoSerenityBDDTests {

    private Actor user;
    private TodoApp todoApp;

    @BeforeEach
    void setUp() {
        todoApp = new TodoApp();
        user = TodoActor.named("Alice");
        user.can(TodoAppUser.using(todoApp));
    }

    @Test
    @DisplayName("When Alice adds 'Buy milk' it appears and can be deleted")
    void userCanAddAndDeleteTodoItem() {
        String todoDescription = "Buy milk";

        user.attemptsTo(AddTodoTask.withDescription(todoDescription));
        assertThat(TodoQuestions.todoItemWithTextExists(todoDescription).answeredBy(user))
                .as("the new todo should be visible")
                .isTrue();

        user.attemptsTo(DeleteTodoTask.theTodoContaining(todoDescription));
        assertThat(TodoQuestions.todoItemWithTextExists(todoDescription).answeredBy(user))
                .as("the deleted todo should disappear")
                .isFalse();
    }
}

package com.todo.uat.tests;

import com.todo.uat.domain.TodoApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoAppTest {

    private TodoApp todoApp;

    @BeforeEach
    void setUp() {
        todoApp = new TodoApp();
    }

    @Test
    @DisplayName("Add a new todo and verify it appears in the list")
    void addTodoAndVerify() {
        String todoDescription = "Buy groceries";

        todoApp.addTodo(todoDescription);

        assertThat(todoApp.containsTodo(todoDescription))
                .as("the todo should be present after adding it")
                .isTrue();
        assertThat(todoApp.size())
                .as("only one todo has been added")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Delete a persisted todo and verify it is removed")
    void deleteTodoAndVerify() {
        String todoDescription = "Schedule meeting";
        todoApp.addTodo(todoDescription);

        boolean removed = todoApp.deleteTodo(todoDescription);

        assertThat(removed).isTrue();
        assertThat(todoApp.containsTodo(todoDescription)).isFalse();
        assertThat(todoApp.size()).isZero();
    }

    @Test
    @DisplayName("Simulate persistence by restoring from a snapshot and verifying state")
    void verifyPersistence() {
        String todoDescription = "Pay bills";
        todoApp.addTodo(todoDescription);

        TodoApp restored = TodoApp.fromSnapshot(todoApp.snapshot());

        assertThat(restored.containsTodo(todoDescription)).isTrue();
        assertThat(restored.size()).isEqualTo(1);
    }
}

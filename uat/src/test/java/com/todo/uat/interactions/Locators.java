package com.todo.uat.interactions;

import org.openqa.selenium.By;

public class Locators {

    public static final By NEW_TODO_INPUT = By.cssSelector("[data-testid='new-todo']");
    public static final By ADD_TODO_BUTTON = By.cssSelector("[data-testid='add-todo']");
    public static final By TODO_LIST_CONTAINER = By.cssSelector("[data-testid='todo-list']");
    public static final By TODO_ITEM = By.cssSelector("[data-testid='todo-item']");
    public static final By DELETE_TODO_BUTTON = By.cssSelector("[data-testid='delete-todo']");

    // Dynamic locators
    public static By todoItemContainingText(String text) {
        // This locator finds a todo item that contains the specified text
        // It looks for a span within a todo-item div that has the text.
        // We then select the parent todo-item div.
        return By.xpath(String.format("//li[@data-testid='todo-item'][.//span[contains(text(), '%s')]]", text));
    }

    public static By deleteButtonForTodoContainingText(String text) {
        // This locator finds the delete button within a specific todo item identified by its text.
        // It looks for a todo-item div containing the text, and then finds the delete-todo button within it.
        return By.xpath(String.format("//li[@data-testid='todo-item'][.//span[contains(text(), '%s')]]//button[@data-testid='delete-todo']", text));
    }

    private Locators() {
        // Private constructor
    }
}

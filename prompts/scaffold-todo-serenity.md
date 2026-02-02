Scaffold a repository for a UAT demonstration with two distinct modules:

### A) Web App (/web)
- **Tech Stack:** Vanilla HTML5, CSS3 (Modern Flexbox/Grid), and ES6+ JavaScript.
- **Features:** - A Todo list where items are stored in `localStorage`.
    - Include a hidden 'Reset' button or utility function to clear storage for test isolation.
- **Locators:** Use the following `data-testid` attributes strictly:
    - Input: `new-todo`
    - Add Button: `add-todo`
    - List Container: `todo-list`
    - Item: `todo-item`
    - Delete Button: `delete-todo`
- **Constraint:** Ensure JS code is compatible with Node.js 20+.

### B) Serenity BDD Automation (/uat)
- **Tech Stack:** Java 17, Maven 3.9+, Serenity BDD, Selenium, and JUnit 5.
- **Design Pattern:** Implement the **Screenplay Pattern** (Actors, Tasks, Interactions, Questions).
- **Test Scenarios:** 1. Add a new todo and verify it appears.
    2. Delete a todo and verify it is removed.
    3. Verify persistence (add item -> refresh page -> item still exists).
- **Configuration (serenity.conf):**
    - Environment: `default` with `base.url = http://localhost:8000`.
    - Driver: Headless Chrome, 1920x1080 resolution.
    - Screenshots: `BEFORE_AND_AFTER_EACH_STEP`.
- **Reporting:** - Include the Serenity Maven plugin for `aggregate`.
    - Create a script/task to convert the aggregate HTML report to PDF using `google-chrome --headless --print-to-pdf`.

### C) Documentation (README.md)
- Provide clear setup instructions for Java 17 and Node 20.
- List commands for:
    1. Starting the server: `python3 -m http.server 8000 --directory web`
    2. Executing tests: `mvn clean verify`
    3. Generating/Converting reports.
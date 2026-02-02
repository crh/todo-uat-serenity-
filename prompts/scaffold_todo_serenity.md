Scaffold a repo with two parts:

A) Static Todo web app (HTML/CSS/JS only) in /web:
- index.html loads app.js and style.css
- Must use data-testid attributes for stable UI test locators:
- input: data-testid="new-todo"
- add button: data-testid="add-todo"
- list container: data-testid="todo-list"
- each item: data-testid="todo-item" and include text
  - each delete button inside an item: data-testid="delete-todo"
  - Use localStorage so todos persist across reloads
  - Keep UI minimal but clear

B) Serenity BDD + Selenium + JUnit 5 Maven module in /uat:
- pom.xml includes serenity-core and serenity-junit5 and
Serenity Maven plugin
- Tests: 2 acceptance tests
1) add_new_todo_should_appear_in_list
2) delete_existing_todo_should_remove_from_list
  - Use Page Object pattern with TodoPage.java locating
    elements via data-testid
  - Read base URL from serenity.conf via environments.default.base.url (default to http://localhost:8000)
  - Configure WebDriver to use Chrome and run headless
  - Configure Serenity screenshots to
  BEFORE_AND_AFTER_EACH_STEP for rich reporting
  - Generate Serenity report under target/site/serenity
  Also create README.md with commands:
  - Start web server: python3 -m http.server 8000 --directory web
  - Run tests: mvn clean verify
  - Generate report: mvn serenity:aggregate
  - Convert target/site/serenity/index.html to PDF using Chrome headless (--print-to-pdf)
  - Mention using --headless=old if
      print-to-pdf fail

document.addEventListener('DOMContentLoaded', () => {
    const todoInput = document.getElementById('new-todo');
    const addButton = document.getElementById('add-todo');
    const todoList = document.getElementById('todo-list');
    const resetButton = document.getElementById('reset-button');

    const STORAGE_KEY = 'todoApp.todos';

    // --- Load todos from localStorage ---
    function loadTodos() {
        const todos = JSON.parse(localStorage.getItem(STORAGE_KEY) || '[]');
        todos.forEach(addTodoToDOM);
        // Show reset button if there are todos
        if (todos.length > 0) {
            resetButton.style.display = 'block';
        } else {
            resetButton.style.display = 'none';
        }
    }

    // --- Save todos to localStorage ---
    function saveTodos() {
        const todos = [];
        todoList.querySelectorAll('.todo-item').forEach(item => {
            todos.push({
                text: item.querySelector('span').textContent,
                id: item.dataset.id
            });
        });
        localStorage.setItem(STORAGE_KEY, JSON.stringify(todos));
        // Show reset button if there are todos
        if (todos.length > 0) {
            resetButton.style.display = 'block';
        } else {
            resetButton.style.display = 'none';
        }
    }

    // --- Add todo to DOM ---
    function addTodoToDOM(todo) {
        const listItem = document.createElement('li');
        listItem.classList.add('todo-item');
        listItem.dataset.id = todo.id || Date.now().toString(); // Assign unique ID

        const textSpan = document.createElement('span');
        textSpan.textContent = todo.text;

        const deleteButton = document.createElement('button');
        deleteButton.classList.add('delete-todo');
        deleteButton.dataset.testid = 'delete-todo';
        deleteButton.textContent = 'Delete';
        deleteButton.onclick = () => {
            deleteTodoFromDOM(listItem);
        };

        listItem.appendChild(textSpan);
        listItem.appendChild(deleteButton);
        listItem.dataset.testid = 'todo-item'; // Explicitly set data-testid for the item itself
        todoList.appendChild(listItem);
    }

    // --- Add new todo ---
    function addTodo() {
        const todoText = todoInput.value.trim();
        if (todoText) {
            const newTodo = {
                text: todoText,
                id: Date.now().toString() // Simple unique ID generation
            };
            addTodoToDOM(newTodo);
            todoInput.value = ''; // Clear input
            saveTodos();
        }
    }

    // --- Delete todo from DOM ---
    function deleteTodoFromDOM(item) {
        todoList.removeChild(item);
        saveTodos();
    }

    // --- Clear all todos ---
    function clearTodos() {
        todoList.innerHTML = '';
        localStorage.removeItem(STORAGE_KEY);
        saveTodos(); // This will hide the reset button
    }

    // --- Event Listeners ---
    addButton.addEventListener('click', addTodo);
    // Allow adding todos by pressing Enter key
    todoInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            addTodo();
        }
    });
    resetButton.addEventListener('click', clearTodos);

    // --- Initial load ---
    loadTodos();
});

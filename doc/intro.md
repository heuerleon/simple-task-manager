# Project: Simple Task Manager

## Objective:
Develop a basic application to manage daily tasks. This project will allow you to create, view, and delete tasks.

## Key Features:
- **Task Creation**: Implement a function to add new tasks. Each task can have a simple description.
- **Listing Tasks**: Create a function to list all current tasks.
- **Deleting Tasks**: Allow the user to delete a task by an identifier (like a task number).
- **Data Storage**: Use a simple in-memory data structure (like a vector or map) to store tasks. No need for a database.

## More Features to be completed:
- [ ] **Task Prioritization**: Add a priority level to each task (e.g., high, medium, low). Modify your listing function to sort tasks based on their priority.
- [ ] **Due Dates for Tasks**: Implement functionality to add due dates to tasks. Extend the listing function to show overdue tasks in a different color or format.
- [ ] **Task Editing**: Allow the user to edit an existing task’s description, priority, or due date.
- [ ] **Task Completion Status**: Add a feature to mark tasks as completed instead of deleting them. Provide an option to view either all tasks or only active/uncompleted tasks.
- [ ] **Data Persistence**: Instead of an in-memory data structure, store your tasks in a local file. Learn how to read and write data to a file in Clojure.
- [ ] **Task Categories/Tags**: Allow users to assign categories or tags to tasks and filter tasks based on these categories.
- [ ] **Recurring Tasks**: Implement functionality for tasks that recur on a regular basis (daily, weekly, monthly). These tasks should automatically reset after their due date passes.
- [ ] **Command-Line Interface (CLI)**: Enhance the user interface of your application. Make it a more user-friendly CLI app with better input handling, help commands, and clear output formatting.
- [ ] **Unit Testing**: Write unit tests for your functions using a Clojure testing framework like clojure.test. This is a great practice to ensure your functions work as expected.
- [ ] **Task Notifications**: Implement a simple notification system that alerts the user when a task is due soon or overdue.
- [ ] **Concurrency Features**: Explore Clojure's concurrency features by adding functionality that might benefit from parallel execution, like batch processing of tasks.
- [ ] **Web Interface**: If you're feeling ambitious, create a simple web interface for your task manager using Clojure’s web libraries like Ring and Compojure.
- [ ] **Interactive Task Scheduler**: Implement an interactive calendar-like interface to schedule and view tasks.
- [ ] **Analytics and Reporting**: Add functionality to generate reports, like the number of tasks completed in a week or the distribution of tasks by priority.
- [ ] **User System**: If you want to go a step further, introduce a simple user system where multiple users can manage their own tasks independently.
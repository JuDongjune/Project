import React from "react";
import TodoItem from "./TodoItem";
import TodoCategory from "./TodoCategory";

const TodoList = ({ date, todos, setTodoData }) => {
  const dateStr = date.toISOString().split("T")[0];

  const toggleDone = (id) => {
    setTodoData((prev) => ({
      ...prev,
      [dateStr]: prev[dateStr].map((todo) =>
        todo.id === id ? { ...todo, done: !todo.done } : todo
      ),
    }));
  };

  const deleteTodo = (id) => {
    setTodoData((prev) => ({
      ...prev,
      [dateStr]: prev[dateStr].filter((todo) => todo.id !== id),
    }));
  };

  return (
    <div className="todo-list">
      <h3 className="todo-date">📅 {dateStr}의 할 일</h3>
      <TodoCategory todos={todos} />
      {todos.map((todo) => (
        <TodoItem
          key={todo.id}
          todo={todo}
          onToggle={() => toggleDone(todo.id)}
          onDelete={() => deleteTodo(todo.id)}
        />
      ))}
    </div>
  );
};

export default TodoList;

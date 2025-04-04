import React from "react";

const TodoItem = ({ todo, onToggle, onDelete }) => {
  return (
    <div className={`todo-item ${todo.done ? "done" : ""}`}>
      <span className="emoji">{todo.emoji}</span>
      <span className="text">{todo.text}</span>
      <input type="checkbox" checked={todo.done} onChange={onToggle} />
      <button className="delete-btn" onClick={onDelete}>✖</button>
    </div>
  );
};

export default TodoItem;
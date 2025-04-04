import React from "react";

const TodoCategory = ({ todos }) => {
  const categories = [...new Set(todos.map((t) => t.emoji))];

  return (
    <div className="todo-categories">
      {categories.map((emoji) => (
        <span className="category-tag" key={emoji}>
          {emoji}
        </span>
      ))}
    </div>
  );
};

export default TodoCategory;
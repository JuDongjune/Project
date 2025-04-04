import React from "react";
import "../../css/MoodSelector.css";

const moods = [
  { icon: "😄", label: "행복" },
  { icon: "😐", label: "보통" },
  { icon: "😢", label: "슬픔" },
  { icon: "😡", label: "화남" },
  { icon: "😴", label: "피곤" },
  { icon: "🥰", label: "설렘" },
];

const MoodSelector = ({ selectedMood, onSelect }) => {
  return (
    <div className="mood-selector">
      <h4>오늘 기분은?</h4>
      <div className="mood-list">
        {moods.map((mood) => (
          <button
            key={mood.label}
            className={`mood-button ${selectedMood === mood.icon ? "selected" : ""}`}
            onClick={() => onSelect(mood.icon)}
          >
            <span className="emoji">{mood.icon}</span>
            <span className="label">{mood.label}</span>
          </button>
        ))}
      </div>
    </div>
  );
};

export default MoodSelector;

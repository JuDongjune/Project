import React from "react";

const ProgressCircle = ({ percent }) => {
  const radius = 10;
  const stroke = 2;
  const normalized = Math.min(100, Math.max(0, percent));
  const circumference = 2 * Math.PI * radius;
  const strokeDashoffset = circumference - (normalized / 100) * circumference;

  return (
    <svg height="24" width="24">
      <circle
        stroke="#eee"
        fill="transparent"
        strokeWidth={stroke}
        r={radius}
        cx="12"
        cy="12"
      />
      <circle
        stroke="#4caf50"
        fill="transparent"
        strokeWidth={stroke}
        strokeDasharray={circumference}
        strokeDashoffset={strokeDashoffset}
        r={radius}
        cx="12"
        cy="12"
      />
    </svg>
  );
};

export default ProgressCircle;

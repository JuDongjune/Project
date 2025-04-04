import React, { useState } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import "../../css/DiaryCalendarView.css";

const DiaryCalendarView = ({ diaries }) => {
  const [selectedDate, setSelectedDate] = useState(null);

  const formatDate = (date) => date.toISOString().split("T")[0];

  const diaryByDate = diaries.reduce((acc, diary) => {
    const date = diary.date || formatDate(new Date()); // 임시로 오늘 날짜
    if (!acc[date]) acc[date] = [];
    acc[date].push(diary);
    return acc;
  }, {});

  return (
    <div className="calendar-view">
      <Calendar
        onChange={setSelectedDate}
        value={selectedDate}
        tileClassName={({ date }) => {
          const key = formatDate(date);
          return diaryByDate[key] ? "has-diary" : null;
        }}
        tileContent={({ date }) => {
          const key = formatDate(date);
          const diary = diaryByDate[key]?.[0];
          return diary ? <span className="emoji">{diary.mood}</span> : null;
        }}
      />

      {selectedDate && (
        <div className="selected-diary-list">
          <h3>📅 {formatDate(selectedDate)}의 다이어리</h3>
          <ul>
            {(diaryByDate[formatDate(selectedDate)] || []).map((d) => (
              <li key={d.id}>
                <strong>{d.title}</strong>: {d.content}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default DiaryCalendarView;

import React from "react";
import { BarChart, Bar, XAxis, YAxis, Tooltip, PieChart, Pie, Cell, ResponsiveContainer } from "recharts";
import "../../css/DiaryStats.css";

const COLORS = ["#FFB6C1", "#87CEEB", "#FFD700", "#FF8C00", "#32CD32", "#9370DB"];

const DiaryStats = ({ diaries }) => {
  const monthData = diaries.reduce((acc, diary) => {
    const date = new Date(diary.date || new Date());
    const month = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}`;
    acc[month] = (acc[month] || 0) + 1;
    return acc;
  }, {});

  const moodData = diaries.reduce((acc, diary) => {
    const mood = diary.mood || "기타";
    acc[mood] = (acc[mood] || 0) + 1;
    return acc;
  }, {});

  const monthChart = Object.keys(monthData).map((month) => ({ month, count: monthData[month] }));
  const moodChart = Object.keys(moodData).map((mood) => ({ name: mood, value: moodData[mood] }));

  return (
    <div className="diary-stats">
      <h3>📊 월별 다이어리 작성</h3>
      <ResponsiveContainer width="100%" height={220}>
        <BarChart data={monthChart}>
          <XAxis dataKey="month" />
          <YAxis allowDecimals={false} />
          <Tooltip />
          <Bar dataKey="count" fill="#8884d8" radius={[6, 6, 0, 0]} />
        </BarChart>
      </ResponsiveContainer>

      <h3>🎨 감정별 비율</h3>
      <ResponsiveContainer width="100%" height={240}>
        <PieChart>
          <Pie
            data={moodChart}
            dataKey="value"
            nameKey="name"
            outerRadius={80}
            label
          >
            {moodChart.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
};

export default DiaryStats;

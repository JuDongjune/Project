import React, { useState } from "react";
import Calendar from "react-calendar";
import { IoPencil } from "react-icons/io5";
import { FaClipboardList } from "react-icons/fa";
import "react-calendar/dist/Calendar.css";
import "../App.css";
import "../css/CustomCalendar.css";
import "../css/Todo.css";

import Header from "../components/Menu/Header";
import Navbar from "../components/Menu/Navbar";
import ImageSlider from "../components/Slider/ImageSlider";
import StoryList from "../components/Menu/StoryList";
import StoryPopup from "../components/Menu/StoryPopup";
import DiaryModal from "../components/Calendar/DiaryModal";
import DiaryViewer from "../components/Calendar/DiaryViewer";
import MoodSelector from "../components/Mood/MoodSelector";

import TodoList from "../components/Todo/TodoList";
import ProgressCircle from "../components/Todo/ProgressCircle";

const HomeScreen = () => {
  const [viewed, setViewed] = useState([]);
  const [activeIndex, setActiveIndex] = useState(null);
  const [likedStories, setLikedStories] = useState([]);
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [diaryData, setDiaryData] = useState({});
  const [showModal, setShowModal] = useState(false);
  const [showViewer, setShowViewer] = useState(false);
  const [showTodoPopup, setShowTodoPopup] = useState(false);
  const [todoData, setTodoData] = useState({});
  const [newTodoText, setNewTodoText] = useState("");
  const [newTodoEmoji, setNewTodoEmoji] = useState("📌");
  const [filteredCategory, setFilteredCategory] = useState("전체");
  const [hideCompleted, setHideCompleted] = useState(false);
  const [selectedMood, setSelectedMood] = useState("");
  const [moodData, setMoodData] = useState({});

  const users = [
    { id: 1, name: "Me", profileImage: "https://img.vogue.co.kr/vogue/2018/03/style_5aa86f7e5f4ab.jpg" },
    { id: 2, name: "june", profileImage: "https://dimg.donga.com/wps/NEWS/IMAGE/2022/05/11/113339874.2.jpg" },
    { id: 3, name: "jiwon", profileImage: "https://t4.daumcdn.net/thumb/R720x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/cnoC/image/HQXTfNlakNIxXfQyFHdT7oyz5IY.jpg" },
    { id: 4, name: "enzlese", profileImage: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWl86vh2yfCQTOQMOMcee5h4XeiJy22gF2xg&s" },
    { id: 5, name: "sss", profileImage: "https://news.nateimg.co.kr/orgImg/tr/2023/11/17/b111fd9c-75d0-4453-8cd2-f46d3462c6ca.jpg" },
  ];

  const handleStoryClick = (user, index) => {
    setActiveIndex(index);
    if (!viewed.includes(user.id)) {
      setViewed((prev) => [...prev, user.id]);
    }
  };

  const handleDiarySave = (dateStr, entry) => {
    setDiaryData((prev) => ({
      ...prev,
      [dateStr]: [...(prev[dateStr] || []), entry],
    }));
    setShowModal(false);
  };

  const handleMoodSelect = (emoji) => {
    const key = formatDate(selectedDate);
    setSelectedMood(emoji);
    setMoodData((prev) => ({
      ...prev,
      [key]: emoji,
    }));
  };

  const formatDate = (date) => date.toISOString().split("T")[0];

  const handleAddTodo = () => {
    const dateStr = formatDate(selectedDate);
    if (!newTodoText) return;
    const newTodo = {
      id: Date.now(),
      text: newTodoText,
      emoji: newTodoEmoji,
      done: false,
    };
    setTodoData((prev) => ({
      ...prev,
      [dateStr]: [...(prev[dateStr] || []), newTodo],
    }));
    setNewTodoText("");
    setNewTodoEmoji("📌");
  };

  const getVisibleTodos = () => {
    const all = todoData[formatDate(selectedDate)] || [];
    const filtered = filteredCategory === "전체" ? all : all.filter(t => t.emoji === filteredCategory);
    return hideCompleted ? filtered.filter(t => !t.done) : filtered;
  };

  const todayTodos = todoData[formatDate(selectedDate)] || [];
  const completedCount = todayTodos.filter(t => t.done).length;

  return (
    <div className="container">
      <Header />
      <StoryList users={users} viewed={viewed} onClick={(u, i) => handleStoryClick(u, i)} />
      <MoodSelector selectedMood={selectedMood} onSelect={handleMoodSelect} />

      <button onClick={() => setShowModal(true)} className="write-button">
        <IoPencil style={{ fontSize: "3rem" }} />
      </button>

      <button onClick={() => setShowTodoPopup(true)} className="todo-toggle-button">
        <FaClipboardList style={{ fontSize: "2rem" }} />
      </button>

      <div className="calendar-wrapper">
        <Calendar
          onChange={(date) => {
            setSelectedDate(date);
            setShowTodoPopup(true);
          }}
          value={selectedDate}
          className="custom-calendar"
          tileContent={({ date }) => {
            const key = formatDate(date);
            const todos = todoData[key] || [];
            const completed = todos.filter((t) => t.done).length;
            const percentage = todos.length ? (completed / todos.length) * 100 : 0;
            const mood = moodData[key];

            return (
              <div className="calendar-progress-wrapper">
                <ProgressCircle percent={percentage} />
                {mood && <span className="calendar-mood-icon">{mood}</span>}
              </div>
            );
          }}
        />
      </div>

      {showTodoPopup && (
        <div className="todo-popup">
          <div className="todo-popup-content">
            <button className="close-button" onClick={() => setShowTodoPopup(false)}>
              ✖
            </button>
            <div className="todo-form">
              <input
                type="text"
                placeholder="할 일 입력"
                value={newTodoText}
                onChange={(e) => setNewTodoText(e.target.value)}
              />
              <select
                value={newTodoEmoji}
                onChange={(e) => setNewTodoEmoji(e.target.value)}
              >
                <option value="📌">📌 기본</option>
                <option value="📚">📚 공부</option>
                <option value="🛍">🛍 쇼핑</option>
                <option value="🧘‍♀️">🧘‍♀️ 휴식</option>
                <option value="🎮">🎮 게임</option>
                <option value="🍽">🍽 식사</option>
              </select>
              <button onClick={handleAddTodo}>추가</button>
            </div>

            <div className="todo-category-filter">
              <button onClick={() => setFilteredCategory("전체")}>전체</button>
              <button onClick={() => setFilteredCategory("📚")}>📚</button>
              <button onClick={() => setFilteredCategory("🛍")}>🛍</button>
              <button onClick={() => setFilteredCategory("🧘‍♀️")}>🧘‍♀️</button>
              <button onClick={() => setFilteredCategory("🎮")}>🎮</button>
              <button onClick={() => setFilteredCategory("🍽")}>🍽</button>
            </div>

            <div className="todo-stats">
              총 {todayTodos.length}개 중 {completedCount}개 완료됨
            </div>

            <div className="todo-toggle-complete" onClick={() => setHideCompleted(!hideCompleted)}>
              ✅ {hideCompleted ? "완료 숨기기 해제" : "완료된 항목 숨기기"}
            </div>

            <TodoList
              date={selectedDate}
              todos={getVisibleTodos()}
              setTodoData={setTodoData}
            />
          </div>
        </div>
      )}

      {activeIndex !== null && (
        <StoryPopup
          user={users[activeIndex]}
          onClose={() => setActiveIndex(null)}
          onNext={() => {
            const nextIndex = activeIndex + 1;
            if (nextIndex < users.length) {
              handleStoryClick(users[nextIndex], nextIndex);
            } else {
              setActiveIndex(null);
            }
          }}
          likedStories={likedStories}
          setLikedStories={setLikedStories}
        />
      )}

      {showModal && (
        <DiaryModal
          date={selectedDate}
          onClose={() => setShowModal(false)}
          onSave={handleDiarySave}
        />
      )}

      {showViewer && (
        <DiaryViewer
          entries={diaryData[formatDate(selectedDate)] || []}
          onClose={() => setShowViewer(false)}
          likedStories={likedStories}
          setLikedStories={setLikedStories}
        />
      )}

      <Navbar />
    </div>
  );
};

export default HomeScreen;
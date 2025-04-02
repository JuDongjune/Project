import React, { useState } from "react";
import Calendar from "react-calendar";
import { IoPencil } from "react-icons/io5"; // 상단에 추가
import "react-calendar/dist/Calendar.css";
import "../App.css";
import Header from "../components/Menu/Header"; // 헤더 추가
import Navbar from "../components/Menu/Navbar"; // 네비게이션 추가
import ImageSlider from "../components/Slider/ImageSlider"; // 슬라이드 추가
import StoryList from "../components/Menu/StoryList";
import StoryPopup from "../components/Menu/StoryPopup";
import DiaryModal from "../components/Calendar/DiaryModal";
import DiaryViewer from "../components/Calendar/DiaryViewer";
import "../css/CustomCalendar.css";

const HomeScreen = () => {
  const [viewed, setViewed] = useState([]);
  const [activeIndex, setActiveIndex] = useState(null);
  const [likedStories, setLikedStories] = useState([]);
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [diaryData, setDiaryData] = useState({});
  const [showModal, setShowModal] = useState(false);
  const [showViewer, setShowViewer] = useState(false);

const users = [
  {
    id: 1,
    name: "Me",
    profileImage: "https://img.vogue.co.kr/vogue/2018/03/style_5aa86f7e5f4ab.jpg"  },
  {
    id: 2,
    name: "june",
    profileImage: "https://dimg.donga.com/wps/NEWS/IMAGE/2022/05/11/113339874.2.jpg",
  },
  {
    id: 3,
    name: "jiwon",
    profileImage: "https://t4.daumcdn.net/thumb/R720x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/cnoC/image/HQXTfNlakNIxXfQyFHdT7oyz5IY.jpg",
  },
  {
    id: 4,
    name: "enzlese",
    profileImage: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWl86vh2yfCQTOQMOMcee5h4XeiJy22gF2xg&s", // 이미지 없으면 아이콘 표시
  },
  {
    id: 5,
    name: "sss",
   profileImage: "https://news.nateimg.co.kr/orgImg/tr/2023/11/17/b111fd9c-75d0-4453-8cd2-f46d3462c6ca.jpg",
  },
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

  const formatDate = (date) => date.toISOString().split("T")[0];

  return (
    <div className="container">
      <Header />
      <StoryList users={users} viewed={viewed} onClick={(u, i) => handleStoryClick(u, i)} />

      <button onClick={() => setShowModal(true)} className="write-button">
        <IoPencil style={{ fontSize: "3rem" }} />
      </button>
  <div className="calendar-wrapper">
      <Calendar
        onChange={setSelectedDate}
        value={selectedDate}
        className="custom-calendar"
        tileClassName={({ date }) => {
          const key = formatDate(date);
          return diaryData[key] ? "has-diary" : null;
        }}
        onClickDay={() => setShowViewer(true)}
      />
  </div>
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
      <ImageSlider/>
      <Navbar />
    </div>
  );
};

export default HomeScreen;

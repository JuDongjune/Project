import React, { useState } from "react";
import Header from "../components/Menu/Header";
import Navbar from "../components/Menu/Navbar";
import FriendListPopup from "../components/Friends/FriendListPopup";
import DiaryPopup from "../components/Diary/DiaryPopup";
import "../css/ProfileScreen.css";
import DiaryStats from "../components/Stats/DiaryStats";
import DiaryCalendarView from "../components/Calendar/DiaryCalendarView";

const dummyUser = {
  name: "Me",
  profileImage: "https://img.vogue.co.kr/vogue/2018/03/style_5aa86f7e5f4ab.jpg",
  bio: "안녕하세요, 감성 다이어리를 쓰는 사람입니다.",
  diaries: [
    { id: 1, title: "벚꽃이 피었다", content: "오늘은 봄 느낌이 났다.", image: "https://picsum.photos/id/1011/400/250", mood: "🌸" },
    { id: 2, title: "비오는 날", content: "촉촉한 공기에 기분이 좋았다.", image: "https://picsum.photos/id/1012/400/250", mood: "☔" },
    { id: 3, title: "노을을 봤다", content: "붉게 물든 하늘 아래 걷는 기분이란.", image: "https://picsum.photos/id/1013/400/250", mood: "🌇" },
  ],
  friends: [
    { id: 1, name: "june", profileImage: "https://picsum.photos/id/1025/50" },
    { id: 2, name: "jiwon", profileImage: "https://picsum.photos/id/1027/50" },
    { id: 3, name: "enzlese", profileImage: "https://picsum.photos/id/1028/50" },
  ],
};

const ProfileScreen = () => {
  const [isEditing, setIsEditing] = useState(false);
  const [bio, setBio] = useState(dummyUser.bio);
  const [showFriendPopup, setShowFriendPopup] = useState(false);
  const [profileImage, setProfileImage] = useState(dummyUser.profileImage);
  const [friends, setFriends] = useState(dummyUser.friends);
  const [selectedDiary, setSelectedDiary] = useState(null);

  const handleProfileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setProfileImage(imageUrl);
    }
  };

  const handleDiaryClick = (diary) => {
    setSelectedDiary(diary);
  };

  const handleRemoveFriend = (id) => {
    setFriends(prev => prev.filter(f => f.id !== id));
  };

  return (
    <div className="profile-screen">
      <Header />

      <div className="profile-header">
        <div className="profile-img-wrapper">
          <label htmlFor="profile-upload">
            <img src={profileImage} alt="profile" className="profile-img" />
            <span className="upload-icon">＋</span>
          </label>
          <input
            type="file"
            accept="image/*"
            id="profile-upload"
            style={{ display: "none" }}
            onChange={handleProfileChange}
          />
        </div>

        <h2>{dummyUser.name}</h2>
        {isEditing ? (
          <textarea
            className="bio-edit"
            value={bio}
            onChange={(e) => setBio(e.target.value)}
          />
        ) : (
          <p className="bio-text">{bio}</p>
        )}
        <button onClick={() => setIsEditing(!isEditing)} className="edit-btn">
          {isEditing ? "저장" : "프로필 편집"}
        </button>
      </div>

      <div className="profile-stats">
        <span>📖 게시물 {dummyUser.diaries.length}</span>
        <button className="friend-btn" onClick={() => setShowFriendPopup(true)}>
          👥 친구 {friends.length}
        </button>
      </div>

      <div className="diary-list">
        {dummyUser.diaries.map((d) => (
          <div key={d.id} className="diary-card" onClick={() => handleDiaryClick(d)}>
            <img src={d.image} alt={d.title} className="diary-img" />
            <div className="diary-content">
              <h4>{d.title}</h4>
              <p>{d.content}</p>
            </div>
          </div>
        ))}
      </div>

      {showFriendPopup && (
        <FriendListPopup
          friends={friends}
          onClose={() => setShowFriendPopup(false)}
          onRemove={handleRemoveFriend}
        />
      )}

      {selectedDiary && (
        <DiaryPopup
          diary={selectedDiary}
          onClose={() => setSelectedDiary(null)}
        />
      )}
<div className="profile-analytics">
  <DiaryCalendarView diaries={dummyUser.diaries} />
</div>
      <Navbar />
    </div>
  );
};

export default ProfileScreen;

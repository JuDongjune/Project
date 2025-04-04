import React, { useState } from "react";
import "../css/SearchScreen.css";
import Header from "../components/Menu/Header";
import Navbar from "../components/Menu/Navbar";
import ProfilePopup from "../components/Friends/ProfilePopup";

const dummyUsers = [
  { id: 1, name: "june", profileImage: "https://picsum.photos/id/101/100" },
  { id: 2, name: "jiwon", profileImage: "https://picsum.photos/id/102/100" },
  { id: 3, name: "enzlese", profileImage: "https://picsum.photos/id/103/100" },
];

const SearchScreen = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedUser, setSelectedUser] = useState(null);

  const filteredUsers = dummyUsers.filter((user) =>
    user.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="search-screen">
      <Header />
      <div className="search-input-wrapper">
        <input
          type="text"
          placeholder="친구 이름을 검색하세요"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <ul className="search-result-list">
        {filteredUsers.map((user) => (
          <li key={user.id} className="search-result-item" onClick={() => setSelectedUser(user)}>
            <img src={user.profileImage} alt={user.name} className="profile-thumb" />
            <span>{user.name}</span>
          </li>
        ))}
      </ul>

      {selectedUser && (
        <ProfilePopup user={selectedUser} onClose={() => setSelectedUser(null)} />
      )}
      <Navbar />
    </div>
  );
};

export default SearchScreen;

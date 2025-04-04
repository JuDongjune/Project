import React from "react";
import "../../css/Navbar.css";
import { IoHomeOutline, IoSearchOutline, IoNewspaperOutline, IoPersonOutline } from "react-icons/io5";
import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();

  return (
    <div className="navbar">
      <button className="nav-button" onClick={() => navigate("/")}>
        <IoHomeOutline size={20} />
      </button>
      <button className="nav-button" onClick={() => navigate("/SearchScreen")}> {/* 🔍 검색화면으로 이동 */}
        <IoSearchOutline size={20} />
      </button>
      <button className="nav-button" onClick={() => navigate("/NotificationScreen")}> {/* 📰 알림 화면 */}
        <IoNewspaperOutline size={20} />
      </button>
      <button className="nav-button" onClick={() => navigate("/ProfileScreen")}> {/* 👤 프로필 (임시) */}
        <IoPersonOutline size={20} />
      </button>
    </div>
  );
};

export default Navbar;
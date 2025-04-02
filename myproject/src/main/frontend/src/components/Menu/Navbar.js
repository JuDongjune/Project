import React from "react";
import "../../css/Navbar.css"; // ✅ CSS 적용
import { IoHomeOutline, IoSearchOutline, IoCartOutline, IoPersonOutline, IoNewspaperOutline} from "react-icons/io5"; // 아이콘 추가
import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();

  return (
    <div className="navbar">
      <button className="nav-button" onClick={() => navigate("/")}>
        <IoHomeOutline size={20} />
      </button>
      <button className="nav-button" onClick={() => navigate("/search")}>
        <IoSearchOutline size={20} />
      </button>
      <button className="nav-button" onClick={() => navigate("/cart")}>
        <IoNewspaperOutline size={20} />
      </button>
      <button className="nav-button" onClick={() => navigate("/cart")}>
        <IoPersonOutline size={20} />
      </button>
    </div>
  );
};

export default Navbar;

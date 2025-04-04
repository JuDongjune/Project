import React from "react";
import { useNavigate } from "react-router-dom";
import "../../css/Header.css";
import { IoChevronBack, IoEllipsisHorizontal } from "react-icons/io5";

const Header = () => {
  const navigate = useNavigate();

  return (
    <div className="header">
      {/* 왼쪽: 뒤로가기 버튼 */}
      <button className="header-button" onClick={() => navigate(-1)}>
        <IoChevronBack size={25} />
      </button>

      {/* 가운데: 로고 */}
      <h1 className="header-logo" onClick={() => navigate("/home")}>Daily Chapter</h1>

      {/* 오른쪽: 옵션 버튼 */}
      <button className="header-button" onClick={() => navigate("/cart")}> {/* 우측 메뉴 → 임시로 cart */}
        <IoEllipsisHorizontal size={25} />
      </button>
    </div>
  );
};

export default Header;
import React from "react";
import { useNavigate } from "react-router-dom"; // React Router 사용
import "../../css/Header.css"; // ✅ 경로 수정!
import { IoChevronBack, IoCartOutline, IoEllipsisHorizontal } from "react-icons/io5"; // 아이콘 추가

const Header = () => {
  const navigate = useNavigate(); // 뒤로가기 기능

  return (
    <div className="header">
      {/* 왼쪽: 뒤로가기 버튼 */}
      <button className="header-button" onClick={() => navigate(-1)}>
        <IoChevronBack size={25} />
      </button>

      {/* 가운데: 로고 */}
      <h1 className="header-logo">Daily Chapter</h1>

      {/* 오른쪽: 목록 버튼 */}
      <button className="header-button" onClick={() => navigate("/cart")}>
        <IoEllipsisHorizontal size={25} />
      </button>
    </div>
  );
};

export default Header;

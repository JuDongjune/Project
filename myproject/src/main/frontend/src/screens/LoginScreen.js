import React from "react";
import { useNavigate } from "react-router-dom";
import "../css/LoginScreen.css"; // 스타일 분리
import logo from "../assets/image/Login/loginlogo.png"; // ✅ import 방식

const LoginScreen = () => {
    const navigate = useNavigate();

    const handleLogin = () => {
        navigate("/home"); // 로그인 후 홈 화면으로 이동
    };

    return (
        <div className="login-container">
            {/* 로고 */}
            <img src={logo} alt="Logo" className="login-logo" />

            {/* 로그인 입력 폼 */}
            <input type="text" placeholder="Username" className="login-input" />
            <input type="password" placeholder="Password" className="login-input" />

            {/* 로그인 버튼 */}
            <button className="login-button" onClick={handleLogin}>
                LOGIN
            </button>

            {/* 회원가입 & 아이디 찾기 - 같은 줄에 정렬 */}
            <div className="login-links">
              <p onClick={() => navigate("/register")} style={{ cursor: "pointer" }}>SIGN UP</p>
              <span className="divider">/</span>
              <p>FIND ID</p>
            </div>
        </div>
    );
};

export default LoginScreen;

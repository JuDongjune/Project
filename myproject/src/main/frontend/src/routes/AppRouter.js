import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import VideoScreen from "../screens/VideoScreen";
import HomeScreen from "../screens/HomeScreen";
import LoginScreen from "../screens/LoginScreen"; // ✅ 로그인 화면 추가
import RegisterScreen from "../screens/RegisterScreen";

const AppRouter = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<VideoScreen />} />
                <Route path="/login" element={<LoginScreen />} /> {/* 로그인 페이지 */}
                <Route path="/home" element={<HomeScreen />} />
                <Route path="/register" element={<RegisterScreen />} />
            </Routes>
        </Router>
    );
};

export default AppRouter;
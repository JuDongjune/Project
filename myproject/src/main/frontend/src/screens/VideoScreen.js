import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/VideoScreen.css";
import startVideo from "../assets/video/start.mp4"; // ✅ import 방식

const VideoScreen = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      navigate("/login");
    }, 6000);
    return () => clearTimeout(timer);
  }, [navigate]);

  return (
    <div className="background"> {/* ✅ 배경 추가 */}
      <div className="video-container">
        <video autoPlay muted className="video">
          <source src={startVideo} type="video/mp4" />
        </video>
      </div>
    </div>
  );
};

export default VideoScreen;

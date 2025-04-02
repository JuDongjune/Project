import React, { useRef, useState, useEffect } from "react";
import {
  IoPersonCircleOutline,
  IoPersonCircle,
  IoChevronBack,
  IoChevronForward,
} from "react-icons/io5";
import "../../css/Story.css";

const StoryList = ({ users, viewed, onClick }) => {
  const scrollRef = useRef();
  const [isLeftDisabled, setIsLeftDisabled] = useState(true);
  const [isRightDisabled, setIsRightDisabled] = useState(false);

  // 좌우 스크롤 상태 업데이트
  const updateButtonState = () => {
    const el = scrollRef.current;
    if (!el) return;
    setIsLeftDisabled(el.scrollLeft <= 0);
    setIsRightDisabled(el.scrollLeft + el.offsetWidth >= el.scrollWidth - 1);
  };

  useEffect(() => {
    updateButtonState();
    const el = scrollRef.current;
    if (el) el.addEventListener("scroll", updateButtonState);
    return () => {
      if (el) el.removeEventListener("scroll", updateButtonState);
    };
  }, []);

  const scrollLeft = () => {
    scrollRef.current.scrollBy({ left: -150, behavior: "smooth" });
  };

  const scrollRight = () => {
    scrollRef.current.scrollBy({ left: 150, behavior: "smooth" });
  };

  return (
    <div className="story-list-wrapper">
      <button
        className="story-scroll-button"
        onClick={scrollLeft}
        disabled={isLeftDisabled}
      >
        <IoChevronBack style={{ fontSize: "1.5rem" }} />
      </button>

      <div className="story-scroll-container" ref={scrollRef}>
        <div className="story-list">
          {users.map((user, index) => {
            const viewedClass = viewed.includes(user.id)
              ? "story-border viewed"
              : "story-border not-viewed";

            const hasProfile =
              user.profileImage && user.profileImage.trim() !== "";

            return (
              <div
                key={user.id}
                className="story-item"
                onClick={() => onClick(user, index)}
              >
                <div className={viewedClass}>
                  {hasProfile ? (
                    <img
                      src={user.profileImage}
                      alt={user.name}
                      className="story-profile"
                    />
                  ) : viewed.includes(user.id) ? (
                    <IoPersonCircle className="story-profile-icon" />
                  ) : (
                    <IoPersonCircleOutline className="story-profile-icon" />
                  )}
                </div>
                <p className="story-username">{user.name}</p>
              </div>
            );
          })}
        </div>
      </div>

      <button
        className="story-scroll-button"
        onClick={scrollRight}
        disabled={isRightDisabled}
      >
        <IoChevronForward style={{ fontSize: "1.5rem" }} />
      </button>
    </div>
  );
};

export default StoryList;

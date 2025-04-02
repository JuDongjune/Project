import React, { useEffect, useState } from "react";
import "../../css/Story.css";

const StoryPopup = ({ user, onClose, onNext, likedStories, setLikedStories }) => {
  const [autoCloseTime] = useState(5000); // 자동 넘김
  const [comment, setComment] = useState("");
  const [comments, setComments] = useState([]);
  const isLiked = likedStories.includes(user.id);

  useEffect(() => {
    const timer = setTimeout(() => {
      onNext();
    }, autoCloseTime);
    return () => clearTimeout(timer);
  }, [user, onNext, autoCloseTime]);

  const handleLike = () => {
    if (!isLiked) {
      setLikedStories((prev) => [...prev, user.id]);
    }
  };

  const handleCommentSubmit = (e) => {
    e.preventDefault();
    if (comment.trim() !== "") {
      setComments((prev) => [...prev, comment]);
      setComment("");
    }
  };

  return (
    <div className="story-overlay">
      <div className="story-popup fade-in">
        <img
          src={`https://picsum.photos/400/300?random=${user.id}`}
          alt="story"
          className="story-image"
        />
        <p className="story-username">{user.name}'s Story</p>

        <button
          className={`like-button ${isLiked ? "liked" : ""}`}
          onClick={handleLike}
          disabled={isLiked}
        >
          ❤️ {isLiked ? "Liked" : "Like"}
        </button>

        {/* ✅ 댓글 목록 */}
        <div className="comment-list">
          {comments.map((cmt, idx) => (
            <div className="comment" key={idx}>
              <span className="comment-user">You:</span> {cmt}
            </div>
          ))}
        </div>

        {/* ✅ 댓글 입력창 */}
        <form className="comment-form" onSubmit={handleCommentSubmit}>
          <input
            type="text"
            placeholder="Write a comment..."
            value={comment}
            onChange={(e) => setComment(e.target.value)}
          />
          <button type="submit">Send</button>
        </form>

        <button className="close-button" onClick={onClose}>✕</button>
      </div>
    </div>
  );
};

export default StoryPopup;

import React, { useState } from "react";
import "../../css/Diary.css";
import logo from "../../assets/image/Login/loginlogo.png";
import { IoSaveOutline, IoClose, IoCheckmark } from "react-icons/io5";

const DiaryModal = ({ date, onClose, onSave }) => {
  const [title, setTitle] = useState("");
  const [text, setText] = useState("");
  const [image, setImage] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();
    const entry = {
      title,
      text,
      image: image ? URL.createObjectURL(image) : null,
      author: "Me",
    };
    const key = date.toISOString().split("T")[0];
    onSave(key, entry);
  };

  return (
    <div className="diary-modal-overlay">
      <form className="diary-modal" onSubmit={handleSubmit}>
        <img src={logo} alt="logo" className="diary-logo" />
        <textarea
          placeholder="제목"
          className="diary-input title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <textarea
          placeholder="내용"
          className="diary-input content"
          value={text}
          onChange={(e) => setText(e.target.value)}
        />
        <input
          type="file"
          accept="image/*"
          onChange={(e) => setImage(e.target.files[0])}
          className="diary-file"
        />
        <div className="modal-buttons">
          <button type="submit"><IoCheckmark size={20} /></button>
          <button type="button" onClick={onClose}><IoClose size={22} /></button>
        </div>
      </form>
    </div>
  );
};

export default DiaryModal;

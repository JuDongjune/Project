import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/RegisterScreen.css";
import logo from "../assets/image/Login/loginlogo.png";
import { FaEye, FaEyeSlash, FaCheck, FaTimes } from "react-icons/fa";

const RegisterScreen = () => {
  const navigate = useNavigate();

  const [id, setId] = useState("");
  const [nickname, setNickname] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phone, setPhone] = useState("");
  const [codeSent, setCodeSent] = useState(false);
  const [timer, setTimer] = useState(0);
  const [codeInputs, setCodeInputs] = useState(["", "", "", ""]);

  const isValidId = id === "1234";
  const isValidNickname = nickname === "1234";
  const isPasswordMatch = password === confirmPassword;
  const isCodeCorrect = codeInputs.join("") === "1234";

  const handleSendCode = () => {
    if (phone && timer === 0) {
      setCodeSent(true);
      setTimer(60); // Start countdown from 60 seconds
    }
  };

  useEffect(() => {
    let countdown;
    if (timer > 0) {
      countdown = setInterval(() => {
        setTimer((prev) => prev - 1);
      }, 1000);
    }
    return () => clearInterval(countdown);
  }, [timer]);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleRegister = () => {
    if (isValidId && isValidNickname && isPasswordMatch && codeSent && isCodeCorrect) {
      navigate("/home");
    }
  };

  const handleCodeChange = (index, value) => {
    const newInputs = [...codeInputs];
    newInputs[index] = value;
    setCodeInputs(newInputs);
  };

  return (
    <div className="register-container">
      <img src={logo} alt="Logo" className="register-logo" />

      {/* ID */}
      <label className="input-label">ID</label>
      <div className="input-wrapper">
        <input
          type="text"
          placeholder="ID"
          value={id}
          onChange={(e) => setId(e.target.value)}
          className="register-input"
        />
        {id && (
          <span className={`status-icon ${isValidId ? "success" : "error"}`}>
            {isValidId ? <FaCheck color="white" size={12} /> : <FaTimes color="white" size={12} />}
          </span>
        )}
      </div>
      {id && (
        <p className={`register-message ${isValidId ? "success" : "error"}`}>
          {isValidId ? "THIS IS AN AVAILABLE ID." : "ID IS NOT AVAILABLE."}
        </p>
      )}

      {/* PASSWORD */}
      <label className="input-label">PASSWORD</label>
      <div className="input-wrapper">
        <input
          type={showPassword ? "text" : "password"}
          placeholder="PASSWORD"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="register-input"
        />
        <span className="eye-icon" onClick={togglePasswordVisibility}>
          {showPassword ? <FaEyeSlash /> : <FaEye />}
        </span>
      </div>

      {/* CONFIRM PASSWORD */}
      <div className="input-wrapper">
        <input
          type={showPassword ? "text" : "password"}
          placeholder="CONFIRM PASSWORD"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          className="register-input"
        />
        <span className="eye-icon" onClick={togglePasswordVisibility}>
          {showPassword ? <FaEyeSlash /> : <FaEye />}
        </span>
      </div>
      {confirmPassword && (
        <p className={`register-message ${isPasswordMatch ? "success" : "error"}`}>
          {isPasswordMatch ? "PASSWORD MATCHED." : "PASSWORD DO NOT MATCH."}
        </p>
      )}

      {/* NICKNAME */}
      <label className="input-label">NICKNAME</label>
      <div className="input-wrapper">
        <input
          type="text"
          placeholder="NICKNAME"
          value={nickname}
          onChange={(e) => setNickname(e.target.value)}
          className="register-input"
        />
        {nickname && (
          <span className={`status-icon ${isValidNickname ? "success" : "error"}`}>
            {isValidNickname ? <FaCheck color="white" size={12} /> : <FaTimes color="white" size={12} />}
          </span>
        )}
      </div>
      {nickname && (
        <p className={`register-message ${isValidNickname ? "success" : "error"}`}>
          {isValidNickname ? "THIS IS AN AVAILABLE NICKNAME." : "NICKNAME IS NOT AVAILABLE."}
        </p>
      )}

      {/* PHONE */}
      <label className="input-label">PHONE</label>
      <div className="input-wrapper">
        <input
          type="text"
          placeholder="PHONE"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
          className="register-input with-send"
        />
        <button
          className="send-button"
          onClick={handleSendCode}
          disabled={timer > 0}
          style={{ backgroundColor: timer > 0 ? '#ccc' : 'white', color: timer > 0 ? '#888' : 'black' }}
        >
          SEND
        </button>
      </div>
      {codeSent && <p className="register-message success">Verification CODE HAS BEEN SENT.</p>}

      {/* 인증번호 입력 */}
      <div className="code-box">
        <div className="code-box-header">
          <span>{timer > 0 ? `${timer}s` : "0s"}</span>
          <div className="security-icon"></div>
          <button className="refresh-button"></button>
        </div>
        <div className="code-inputs">
          {codeInputs.map((digit, index) => (
            <input
              key={index}
              maxLength="1"
              value={digit}
              onChange={(e) => handleCodeChange(index, e.target.value)}
            />
          ))}
        </div>
      </div>
      {codeInputs.some((v) => v !== "") && (
        <p className={`register-message ${isCodeCorrect ? "success" : "error"}`}>
          {isCodeCorrect ? "Number is correct." : "Number is incorrect."}
        </p>
      )}

      {/* REGISTER 버튼 */}
      <button className="register-button" onClick={handleRegister}>
        REGISTRATION
      </button>
    </div>
  );
};

export default RegisterScreen;

import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { FaCheck, FaTimes } from "react-icons/fa";

const FindAccountScreen = () => {
  const navigate = useNavigate();

  const [phone, setPhone] = useState("");
  const [codeSent, setCodeSent] = useState(false);
  const [timer, setTimer] = useState(0);
  const [codeInputs, setCodeInputs] = useState(["", "", "", ""]);
  const [isCodeCorrect, setIsCodeCorrect] = useState(false);
  const [isPasswordFinding, setIsPasswordFinding] = useState(false); // 비밀번호 찾기 or 아이디 찾기 선택

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

  const handleCodeChange = (index, value) => {
    const newInputs = [...codeInputs];
    newInputs[index] = value;
    setCodeInputs(newInputs);
  };

  const handleFindAccount = () => {
    if (codeInputs.join("") === "1234") {
      setIsCodeCorrect(true);
      // 아이디/비밀번호 찾기 로직
      if (isPasswordFinding) {
        navigate("/reset-password"); // 비밀번호 찾기 후 비밀번호 변경 화면으로 이동
      } else {
        navigate("/show-id"); // 아이디 찾기 후 아이디 화면으로 이동
      }
    } else {
      setIsCodeCorrect(false);
    }
  };

  return (
    <div className="register-container">
      <h2>아이디/비밀번호 찾기</h2>

      {/* 전화번호 입력 */}
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

      {/* 아이디/비밀번호 찾기 버튼 */}
      <div className="find-buttons">
        <button
          className={`find-button ${!isPasswordFinding ? "selected" : ""}`}
          onClick={() => setIsPasswordFinding(false)}
        >
          아이디 찾기
        </button>
        <button
          className={`find-button ${isPasswordFinding ? "selected" : ""}`}
          onClick={() => setIsPasswordFinding(true)}
        >
          비밀번호 찾기
        </button>
      </div>

      {/* 최종 진행 버튼 */}
      <button className="register-button" onClick={handleFindAccount}>
        진행
      </button>
    </div>
  );
};

export default FindAccountScreen;

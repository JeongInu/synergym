import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { login } from "@/api/authApi";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useUserStore } from "@/store/userStore";

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const setUser = useUserStore((state) => state.setUser);
  const navigate = useNavigate();

  const handleLogin = async() => {
    try {
      const response = await login({ email, password });
      const result = response.message;

      if (!email && !password) {
        alert("🤔 이메일이랑 비밀번호를 모두 입력해 주세요!");
        return;
      }

      if (!email) {
        alert("📧 이메일을 입력해 주세요!");
        return;
      }

      if (!password) {
        alert("🔐 비밀번호를 입력해 주세요!");
        return;
      }

      if (result === "s") {
        const whoami = response.username;
        setUser(response);
        navigate("/");
        alert(`🎉 로그인 성공!\n${whoami}님, 다시 오신 걸 환영해요 😊`);
      } else if (result === "m") {
        alert("😥 등록되지 않은 이메일이에요.\n입력한 주소를 다시 확인해주세요!");
      } else if (result === "p") {
        alert("😅 비밀번호가 맞지 않아요.\n다시 한번 확인해볼까요?");
      }

    } catch(err) {
      console.log(`로그인 실패 : ${err}`);
      setError("로그인 실패");
      alert(error);
    }
  }

  return (
    <div className="h-screen bg-black flex items-center justify-center px-4">
      <div className="w-full max-w-sm bg-zinc-900 p-8 rounded-2xl shadow-lg space-y-6">
        <h2 className="text-3xl font-bold text-white text-center">로그인</h2>

        <div className="space-y-4">
          <div>
            <label className="block text-sm text-white mb-1">E-mail</label>
            <Input 
              type="email" 
              placeholder="example@domain.com" 
              className="bg-zinc-800 text-white" 
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              onKeyDown={(e) => {
                if (e.key === "Enter") handleLogin();
              }}/>
          </div>

          <div>
            <label className="block text-sm text-white mb-1">Password</label>
            <Input 
              type="password" 
              placeholder="********" 
              className="bg-zinc-800 text-white" 
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              onKeyDown={(e) => {
                if (e.key === "Enter") handleLogin();
              }}/>
          </div>

          <Button 
            className="w-full bg-blue-500 text-white hover:bg-blue-600"
            onClick={ handleLogin }>
            로그인 
          </Button>
        </div>

        <p className="text-sm text-zinc-400 text-center">
          아직 계정이 없으신가요? <a href="/join" className="text-blue-400 hover:underline">회원가입</a>
        </p>
      </div>
    </div>
  );
};

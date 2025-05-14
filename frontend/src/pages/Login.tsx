import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { login } from "@/api/authApi";
import { useState } from "react";

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async() => {
    try {
      const response = await login({ email, password });
      console.log(response);
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
              onChange={(e) => setEmail(e.target.value)}/>
          </div>

          <div>
            <label className="block text-sm text-white mb-1">Password</label>
            <Input 
              type="password" 
              placeholder="********" 
              className="bg-zinc-800 text-white" 
              value={password}
              onChange={(e) => setPassword(e.target.value)}/>
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

import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

export default function Login() {
  return (
    <div className="h-screen bg-black flex items-center justify-center px-4">
      <div className="w-full max-w-sm bg-zinc-900 p-8 rounded-2xl shadow-lg space-y-6">
        <h2 className="text-3xl font-bold text-white text-center">로그인</h2>

        <div className="space-y-4">
          <div>
            <label className="block text-sm text-white mb-1">E-mail</label>
            <Input type="email" placeholder="example@domain.com" className="bg-zinc-800 text-white" />
          </div>

          <div>
            <label className="block text-sm text-white mb-1">Password</label>
            <Input type="password" placeholder="••••••••" className="bg-zinc-800 text-white" />
          </div>

          <Button className="w-full bg-blue-500 text-white hover:bg-blue-600">
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

import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";

const Header = () => {
const navigate = useNavigate();

  return (
    <div className="absolute top-0 left-0 w-full h-20 flex items-center justify-between px-6 bg-black text-white z-50">
      <div className="flex items-center space-x-2">
        <img src="/images/synergym_logo.png" alt="Logo" className="h-10" />
      </div>

      {/* 메뉴 */}
      <nav className="flex space-x-6">
        <a href="/" className="hover:text-violet-400 transition">사진분석</a>
        <a href="/" className="hover:text-violet-400 transition">커뮤니티</a>
        <a href="/" className="hover:text-violet-400 transition">운동목록</a>
      </nav>

      {/* 로그인 버튼 */}
      <Button 
        variant="outline" 
        className="border-white text-white hover:bg-white hover:text-black transition-all"
        onClick={() => navigate("/login")}>
        로그인
      </Button>
    </div>
  );
};

export default Header;

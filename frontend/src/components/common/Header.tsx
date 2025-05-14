import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";
import { useUserStore } from "@/store/userStore";

const Header = () => {
const navigate = useNavigate();
const user = useUserStore((state) => state.user);
const clearUser = useUserStore((state) => state.clearUser);
const clearUserStorage = useUserStore((state) => state.clearUserStorage);

const handleLogout = () => {
    clearUser();
    clearUserStorage();
};

  return (
    <div className="absolute top-0 left-0 w-full h-20 flex items-center justify-between px-6 bg-black text-white z-50">
      <div className="flex items-center space-x-2">
        <img 
          src="/images/synergym_logo.png" 
          alt="Logo" 
          className="h-10" 
          onClick={() => navigate("/")}/>
      </div>

      {/* 메뉴 */}
      <nav className="flex space-x-6">
        <a href="/" className="hover:text-violet-400 transition">사진분석</a>
        <a href="/posts" className="hover:text-violet-400 transition">커뮤니티</a>
        <a href="/" className="hover:text-violet-400 transition">운동목록</a>
        {user && (
          <a href="/" className="hover:text-violet-400 transition">마이페이지</a>
        )}
      </nav>

      {/* 로그인/로그아웃 버튼 */}
      {user ? (
        <Button
          variant="outline"
          className="border-white text-white hover:bg-white hover:text-black transition-all"
          onClick={handleLogout}
          >
            로그아웃
          </Button>
        ) : (
          <Button
            variant="outline"
            className="border-white text-white hover:bg-white hover:text-black transition-all"
            onClick={() => navigate("/login")}
          >
            로그인
          </Button>
        )}
    </div>
  );
};

export default Header;

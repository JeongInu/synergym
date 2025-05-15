import { Button } from "@/components/ui/button";
import { useNavigate, Link } from "react-router-dom";
import { useUserStore } from "@/store/userStore";
import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuList,
  NavigationMenuTrigger,
  NavigationMenuContent,
  NavigationMenuLink,
} from "@/components/ui/navigation-menu";

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
      <NavigationMenu>
        <NavigationMenuList className="space-x-4">
          <NavigationMenuItem>
            <NavigationMenuLink asChild>
              <Link to="/" className="hover:text-violet-400 transition">
                사진분석
              </Link>
            </NavigationMenuLink>
          </NavigationMenuItem>

          <NavigationMenuItem>
            <NavigationMenuLink asChild>
              <Link to="/posts" className="hover:text-violet-400 transition">
                커뮤니티
              </Link>
            </NavigationMenuLink>
          </NavigationMenuItem>

          <NavigationMenuItem>
            <NavigationMenuLink asChild>
              <Link to="/" className="hover:text-violet-400 transition">
                운동목록
              </Link>
            </NavigationMenuLink>
          </NavigationMenuItem>

          {user && (
            <NavigationMenuItem>
              <NavigationMenuTrigger>마이페이지</NavigationMenuTrigger>
              <NavigationMenuContent>
                <ul className="grid gap-2 p-4 w-48">
                  <li>
                    <NavigationMenuLink asChild>
                      <Link to="/" className="text-sm hover:text-violet-400 transition">
                        회원 정보
                      </Link>
                    </NavigationMenuLink>
                  </li>
                  <li>
                    <NavigationMenuLink asChild>
                      <Link to="/" className="text-sm hover:text-violet-400 transition">
                        사진 분석 기록
                      </Link>
                    </NavigationMenuLink>
                  </li>
                  <li>
                    <NavigationMenuLink asChild>
                      <Link to="/" className="text-sm hover:text-violet-400 transition">
                        루틴 기록
                      </Link>
                    </NavigationMenuLink>
                  </li>
                  <li>
                    <NavigationMenuLink asChild>
                      <Link to="/" className="text-sm hover:text-violet-400 transition">
                        좋아요한 운동
                      </Link>
                    </NavigationMenuLink>
                  </li>
                </ul>
              </NavigationMenuContent>
            </NavigationMenuItem>
          )}
        </NavigationMenuList>
      </NavigationMenu>

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

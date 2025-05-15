import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import api from "../api/axiosInstance"; 
import { useUserStore } from "@/store/userStore";

const PostWrite = () => {
  const navigate = useNavigate();
  const user = useUserStore((state) => state.user); // 로그인한 사용자 정보
  
  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const [error, setError] = useState<string | null>(null);


  const handleSubmit = async () => { // async 키워드 추가
    if (!user) {
      setError("로그인 시 이용 가능합니다.");
      return;
    }
    
    if (!title.trim() || !content.trim()) {
      setError("제목과 내용은 반드시 입력해야 합니다.");
      return;
    }


    // 백엔드로 보낼 데이터 객체
    const postData = {
      title,
      content,
      // 백엔드의 addPost 메서드는 DTO와 userId를 받으므로 DTO에 userId를 포함
      userId: user.id // 실제 사용자 ID 사용
      // createdAt, username 등은 백엔드에서 처리
    };

    try {
      // 백엔드 API 호출하여 게시글 작성
      // POST /posts 엔드포인트 사용, 요청 본문에 postData, 쿼리 파라미터로 userId 전달
      const response = await api.post("/posts", postData, {
          params: {
              userId: user.id // 쿼리 파라미터로 userId 전달
          }
      });

      console.log("게시글 작성 성공:", response.data);
      // 게시글 작성 후 목록 페이지로 이동
      navigate("/posts");
    } catch (err) {
      setError("게시글 작성 중 오류가 발생했습니다.");
      console.error("게시글 작성 오류:", err);
      // 오류 처리 로직 추가 (예: 사용자에게 오류 메시지 표시)
    }
  };


  return (
    <div className="bg-black text-white min-h-screen">
    
      <Header />

    <br/>
    <br/>
    <br/>
    <br/>
    
      
      <main className="container mx-auto px-4 py-12">
        <div className="max-w-3xl mx-auto bg-gray-900 p-6 rounded-lg shadow-md">
          <h1 className="text-2xl font-bold mb-6">새 게시글 작성</h1>

          {error && <div className="text-red-500 mb-4">{error}</div>}

          {/* 제목 입력 */}
          <div className="mb-4">
            <Input
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-md"
              placeholder="제목을 입력하세요"
            />
          </div>

          {/* 내용 입력 */}
          <div className="mb-4">
            <textarea
              value={content}
              onChange={(e) => setContent(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-md h-48"
              placeholder="내용을 입력하세요"
            ></textarea>
          </div>

          {/* 제출 버튼 */}
          <div className="mt-6 flex justify-end">
            <Button
              onClick={handleSubmit}
              className="bg-blue-500 hover:bg-blue-600 text-white px-6 py-2 rounded-md"
            >
              게시글 작성
            </Button>
          </div>
        </div>
      </main>

      <Footer />
    </div>
  );
};

export default PostWrite;

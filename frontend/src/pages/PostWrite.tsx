import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

const PostWrite = () => {
  const navigate = useNavigate();
  
  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = () => {
    // 게시글 제목과 내용이 비어 있지 않은지 확인
    if (!title.trim() || !content.trim()) {
      setError("제목과 내용은 반드시 입력해야 합니다.");
      return;
    }

    // 게시글 제출 (모킹된 데이터로 저장 또는 API 연동 필요)
    const newPost = {
      title,
      content,
      username: "사용자1", // 실제 사용자로 변경해야 함
      createdAt: new Date().toISOString(),
    };

    console.log("새 게시글 작성:", newPost);

    // 게시글 작성 후 목록 페이지로 이동
    navigate("/posts");
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

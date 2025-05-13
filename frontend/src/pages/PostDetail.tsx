import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

interface PostType {
  id: number;
  title: string;
  content: string;
  username: string;
  createdAt: string;
  viewCount: number;
}

const PostDetail = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  const [post, setPost] = useState<PostType | null>(null);
  const [loading, setLoading] = useState(true);
  const [editMode, setEditMode] = useState(false);
  const [editedTitle, setEditedTitle] = useState("");
  const [editedContent, setEditedContent] = useState("");

  // 게시글 불러오기 (Mock)
  const fetchPost = async () => {
    setLoading(true);
    try {
      // 실제 API 호출 시 여기에 fetch 로직 대체
      const mockPost: PostType = {
        id: Number(id),
        title: `게시글 제목 ${id}`,
        content: `게시글 내용 ${id}`,
        username: "사용자1",
        createdAt: "2025-05-13T12:00:00",
        viewCount: 123,
      };
      setPost(mockPost);
      setEditedTitle(mockPost.title);
      setEditedContent(mockPost.content);
    } catch (err) {
      console.error("게시글 로딩 실패", err);
    } finally {
      setLoading(false);
    }
  };

  const handleSave = () => {
    // 저장 로직 추가(API 호출 등)
    if (post) {
      setPost({
        ...post,
        title: editedTitle,
        content: editedContent,
      });
    }
    setEditMode(false);
  };

  const handleCancel = () => {
    if (post) {
      setEditedTitle(post.title);
      setEditedContent(post.content);
    }
    setEditMode(false);
  };

  useEffect(() => {
    fetchPost();
  }, [id]);

  if (loading || !post) {
    return <div className="text-center py-20 text-white">로딩 중...</div>;
  }

  return (
    <div className="bg-black text-white min-h-screen">
      <Header />
      <br/>
      <br/>
      <br/>
      <br/>
      
      <main className="container mx-auto px-4 py-6">
        <div className="max-w-3xl mx-auto bg-gray-900 p-6 rounded-lg shadow-md">
          <div className="mb-4 flex justify-between items-center">
            <h2 className="text-2xl font-bold">게시글 상세</h2>
            {!editMode && (
              <Button
                onClick={() => setEditMode(true)}
                className="bg-blue-500 hover:bg-blue-600 text-white"
              >
                수정
              </Button>
            )}
          </div>

          <div className="mb-4">
            <label className="block mb-1 text-gray-300">제목</label>
            {editMode ? (
              <Input
                value={editedTitle}
                onChange={(e) => setEditedTitle(e.target.value)}
              />
            ) : (
              <p className="text-lg">{post.title}</p>
            )}
          </div>

          <div className="mb-4">
            <label className="block mb-1 text-gray-300">내용</label>
            {editMode ? (
              <Input
                value={editedContent}
                onChange={(e) => setEditedContent(e.target.value)}
              />
            ) : (
              <p className="whitespace-pre-wrap">{post.content}</p>
            )}
          </div>

          <div className="text-sm text-gray-400 mb-6">
            작성자: {post.username} | 작성일: {post.createdAt.split("T")[0]} | 조회수: {post.viewCount}
          </div>

          {editMode && (
            <div className="flex gap-4">
              <Button onClick={handleSave} className="bg-blue-500 hover:bg-blue-600 text-white">
                저장
              </Button>
              <Button variant="outline" onClick={handleCancel}>
                취소
              </Button>
            </div>
          )}

          {!editMode && (
            <Button
              onClick={() => navigate(-1)}
              className="mt-6 bg-gray-700 hover:bg-gray-600 text-white"
            >
              목록으로
            </Button>
          )}
        </div>
      </main>
      <Footer />
    </div>
  );
};

export default PostDetail;

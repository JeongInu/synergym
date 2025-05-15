import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import { deletePost, getPostById, updatePost, type PostDTO } from "../api/postApi"; 
import { getCommentsByPostId, addComment, updateComment, deleteComment, type CommentDTO } from "../api/commentApi"; // 댓글 API
import { useUserStore } from "@/store/userStore"; // 사용자 정보 가져오기


// 백엔드의 PostDTO에 대응하는 타입 사용
interface PostType extends PostDTO {}

interface CommentType extends CommentDTO {}


interface PostType {
  id: number;
  title: string;
  content: string;
  username: string;
  createdAt: string;
  updatedAt: string;
  viewCount: number;
}

// 댓글 타입 (백엔드 API에 맞춰 수정 필요)
interface CommentType {
  id: number;
  postId: number;
  content: string;
  username: string;
  createdAt: string;
}

const PostDetail = () => {
  const { postId } = useParams<{ postId: string }>();
  const navigate = useNavigate();

  const [post, setPost] = useState<PostType | null>(null);
  const [loading, setLoading] = useState(true);
  const [editMode, setEditMode] = useState(false);
  const [editedTitle, setEditedTitle] = useState("");
  const [editedContent, setEditedContent] = useState("");

  // 댓글 관련 상태 (Mock 데이터 유지)
  const [comments, setComments] = useState<CommentType[]>([]);
  const [newComment, setNewComment] = useState("");
  const [editingCommentId, setEditingCommentId] = useState<number | null>(null);
  const [editedCommentContent, setEditedCommentContent] = useState("");

  const user = useUserStore((state) => state.user);


 // 게시글 조회 (백엔드 API 연결)
  const fetchPost = async () => {
    setLoading(true);
    try {
      // postId가 문자열로 넘어오므로 숫자로 변환하여 API 호출
        const id = parseInt(postId as string);
        if (isNaN(id)) {
            console.error("유효하지 않은 게시글 ID입니다.");
            setLoading(false);
            return;
        }
      const data = await getPostById(id);
      setPost({
        ...data,
        createdAt: data.createdAt || new Date().toISOString(),
        updatedAt: data.updatedAt || new Date().toISOString(),
      });
      setEditedTitle(data.title);
      setEditedContent(data.content);

      // 댓글 DB에서 불러오기
      const dbComments = await getCommentsByPostId(id);
      setComments(dbComments);
    } catch (err) {
      alert("게시글을 불러오는 데 실패했습니다.");
      navigate("/");
    } finally {
      setLoading(false);
    }
  };



// 게시글 수정 (백엔드 API 연결)
const handleSave = async () => {
  if (!post) return; // post 객체가 없으면 저장하지 않음
  if (!user) {
    alert("로그인 후 이용 가능합니다.");
    return;
  }
  try {
      // 백엔드 updatePost API가 Partial<PostDTO>를 받으므로, 변경된 필드만 담아서 보냅니다.
      const updatedPostData: Partial<PostDTO> = {
          title: editedTitle,
          content: editedContent,
          // userId 등 다른 필드는 여기서는 수정하지 않으므로 포함하지 않습니다.
          // 만약 백엔드에서 다른 필드도 필요하다면 추가해야 합니다.
          // 예: userId: post.userId
      };

    await updatePost(post.id, updatedPostData, user.id);
  
    // 상태 업데이트: UI에 변경사항 반영
    setPost((prev) => prev && { ...prev, title: editedTitle, content: editedContent });
    setEditMode(false);

  } catch (error) {
    console.error("게시글 수정 실패", error);
      // 오류 처리 로직 (예: 사용자에게 메시지 표시)
      alert("게시글 수정에 실패했습니다."); // 간단한 알림 예시
  }
};


// 게시글 수정 취소
  const handleCancel = () => {
    if (post) {
      setEditedTitle(post.title);
      setEditedContent(post.content);
    }
    setEditMode(false);
  };

// 게시글 삭제 핸들러 추가 (백엔드 API 연동)
const handleDeletePost = async () => {
    if (!post || !post.id) {
        console.error("삭제할 게시글 ID가 없습니다.");
        return;
    }

    // 사용자에게 삭제 확인을 받는 로직 추가
    if (window.confirm("정말로 이 게시글을 삭제하시겠습니까?")) {
        try {
            await deletePost(post.id); // 백엔드 deletePost 함수 호출
            alert("게시글이 삭제되었습니다.");
            navigate("/posts"); // 삭제 성공 후 목록 페이지로 이동
        } catch (error) {
            console.error("게시글 삭제 실패", error);
            alert("게시글 삭제에 실패했습니다."); // 오류 발생 시 알림
        }
    }
};



// 댓글 추가
  const handleAddComment = async () => {
    if (!user) {
      alert("로그인 후 이용 가능합니다.");
      return;
    }
    if (newComment.trim() === "") return;
    try {
      await addComment({ content: newComment, postId: post!.id, userId: user.id });
      setNewComment("");
      fetchPost(); // 댓글 추가 후 목록 새로고침
    } catch {
      alert("댓글 추가에 실패했습니다.");
    }
  };

  // 댓글 삭제
  const handleDeleteComment = async (id: number) => {
    if (!user) {
      alert("로그인 후 이용 가능합니다.");
      return;
    }
    try {
      await deleteComment(id, user.id);
      fetchPost();
    } catch {
      alert("댓글 삭제에 실패했습니다.");
    }
  };

  // 댓글 수정 모드 진입
  const handleEditComment = (id: number, content: string) => {
    setEditingCommentId(id);
    setEditedCommentContent(content);
  };

  // 댓글 저장
  const handleSaveComment = async () => {
    if (!user || editingCommentId === null) return;
    try {
      await updateComment(editingCommentId, { content: editedCommentContent, userId: user.id });
      setEditingCommentId(null);
      setEditedCommentContent("");
      fetchPost();
    } catch {
      alert("댓글 수정에 실패했습니다.");
    }
  };

  // 댓글 수정 취소
  const handleCancelCommentEdit = () => {
    setEditingCommentId(null);
    setEditedCommentContent("");
  };

  useEffect(() => {
    fetchPost();
    // eslint-disable-next-line
  }, [postId]);

  if (loading || !post) {
    return <div className="text-center py-20 text-white">로딩 중...</div>;
  }

  return (
    <div className="bg-black text-white min-h-screen">
      <Header />
      <br/><br/><br/><br/>
      <main className="container mx-auto px-4 py-6">
        <div className="max-h-96 overflow-y-auto max-w-3xl mx-auto bg-gray-900 p-6 rounded-lg shadow-md">
          <div className="mb-4 flex justify-between items-center">
            <h2>
                {editMode ? (
                    <Input
                    value={editedTitle}
                    onChange={(e) => setEditedTitle(e.target.value)}
                />
                ) : (
                    <p className="text-2xl font-bold">{post.title}</p>
                    )
                }
            </h2>
            
          </div>

          
          <div className="text-sm text-gray-400 mb-4">
            작성자: {post.username} |
            작성일: {post.createdAt ? new Date(post.createdAt).toLocaleDateString() : '날짜 정보 없음'} | {/* 생성일 표시 */}
            수정일: {post.updatedAt ? new Date(post.updatedAt).toLocaleDateString() : '수정 정보 없음'} | {/* 수정일 표시 */}
            조회수: {post.viewCount}

          </div>

          <div className="mb-4">
            <label className="block mb-1 text-gray-300"></label>
            {editMode ? (
              <Textarea
                value={editedContent}
                onChange={(e) => setEditedContent(e.target.value)}
              />
            ) : (
              <p className="whitespace-pre-wrap">{post.content}</p>
            )}
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


          {/* 수정 모드가 아닐 때만 보이게 */}
          {!editMode && (
            <div className="flex gap-4">

            {/* 수정/삭제 버튼: 작성자만 보이게 */}
            {user && post && user.id === post.userId && (
              <div className="flex gap-4">
                <Button 
                  onClick={() => setEditMode(true)}
                  className="bg-blue-500 hover:bg-blue-600 text-white">
                  수정
                </Button>

                <Button
                  onClick={handleDeletePost} 
                  className="bg-red-500 hover:bg-red-600 text-white">
                  삭제
              </Button>
              </div>
            )}

              <Button
                onClick={() => navigate(-1)}
                className="bg-gray-500 hover:bg-gray-600 text-white"
              >
                목록으로
              </Button>
            </div>
          )}
        </div>

        {/* 댓글 섹션 */}
        <div className="max-h-130 overflow-y-auto max-w-3xl mx-auto mt-8 bg-gray-900 p-6 rounded-lg shadow-md">
          <h3 className="text-xl font-semibold mb-4">댓글</h3>
          {comments.map((comment) => (
            <div key={comment.id} className="mb-4 p-4 bg-gray-800 rounded-md flex flex-col gap-2">
              <div className="text-sm text-gray-400">
                {comment.username} | {typeof comment.createdAt === "string" ? comment.createdAt.split("T")[0] : "날짜 정보 없음"}
              </div>
              {editingCommentId === comment.id ? (
                <>
                  <Input
                    value={editedCommentContent}
                    onChange={(e) => setEditedCommentContent(e.target.value)}
                  />
                  <div className="flex gap-2 mt-2">
                    <Button onClick={handleSaveComment} className="bg-blue-500 text-white">
                      저장
                    </Button>
                    <Button variant="outline" onClick={handleCancelCommentEdit}>
                      취소
                    </Button>
                  </div>
                </>
              ) : (
                <>
                  <p>{comment.content}</p>
                  {/* 댓글 작성자만 수정/삭제 버튼 보이게 */}
                  {user && user.username === comment.username && (
                    <div className="flex gap-2">
                      <Button
                        onClick={() => handleEditComment(comment.id, comment.content)}
                        className="bg-blue-500 hover:bg-blue-600 text-white">
                        수정
                      </Button>
                      <Button
                        onClick={() => handleDeleteComment(comment.id)}
                        className="bg-red-500 hover:bg-red-600 text-white">
                        삭제
                      </Button>
                    </div>
                  )}
                </>
              )}
            </div>
          ))}

          {/* 로그인한 사용자만 댓글 입력창/추가 버튼 보이게 */}
          {user && (
            <div className="mt-6 flex items-center gap-2">
              <Input
                value={newComment}
                onChange={(e) => setNewComment(e.target.value)}
                placeholder="댓글을 입력하세요..."
                className="flex-1"
              />
              <Button onClick={handleAddComment} className="bg-blue-500 hover:bg-blue-600 text-white">
                추가
              </Button>
            </div>
          )}
        </div>
      </main>
      <Footer />
    </div>
  );
};

export default PostDetail;
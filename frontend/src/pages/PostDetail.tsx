import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";

interface PostType {
  id: number;
  title: string;
  content: string;
  username: string;
  createdAt: string;
  viewCount: number;
}

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

  const [comments, setComments] = useState<CommentType[]>([]);
  const [newComment, setNewComment] = useState("");
  const [editingCommentId, setEditingCommentId] = useState<number | null>(null);
  const [editedCommentContent, setEditedCommentContent] = useState("");


//   //게시글 조회
//   const fetchPost = async () => {
//   setLoading(true);
//   try {
//     const response = await api.get(`/posts/${postId}`);
//     const data = response.data;
//     setPost(data);
//     setEditedTitle(data.title);
//     setEditedContent(data.content);
//   } catch (err) {
//     console.error("게시글 로딩 실패", err);
//   } finally {
//     setLoading(false);
//   }
// };


 // 게시글 조회 (Mock)
  const fetchPost = async () => {
    setLoading(true);
    try {
      // 실제 API 호출 시 여기에 fetch 로직 대체
      const mockPost: PostType = {
        id: Number(postId),
        title: `게시글 제목 ${postId}`,
        content: `게시글 내용 ${postId}`,
        username: "사용자1",
        createdAt: "2025-05-13T12:00:00",
        viewCount: 123,
      };
      setPost(mockPost);
      setEditedTitle(mockPost.title);
      setEditedContent(mockPost.content);
    const mockComments: CommentType[] = [
        {
          id: 1,
          postId: Number(postId),
          content: "첫 번째 댓글입니다.",
          username: "댓글러1",
          createdAt: "2025-05-13T13:00:00",
        },
        {
          id: 2,
          postId: Number(postId),
          content: "두 번째 댓글이에요!",
          username: "댓글러2",
          createdAt: "2025-05-13T14:00:00",
        },
      ];
      setComments(mockComments);
    } catch (err) {
      console.error("게시글 로딩 실패", err);
    } finally {
      setLoading(false);
    }
  };


// 게시글 수정
// const handleSave = async () => {
//   try {
//     await api.put(`/posts/${post?.id}`, {
//       title: editedTitle,
//       content: editedContent,
//     });
//     setPost((prev) => prev && { ...prev, title: editedTitle, content: editedContent });
//     setEditMode(false);
//   } catch (error) {
//     console.error("게시글 수정 실패", error);
//   }
// };

// 게시글 수정(Mock)
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

//게시글 삭제
  const handleCancel = () => {
    if (post) {
      setEditedTitle(post.title);
      setEditedContent(post.content);
    }
    setEditMode(false);
  };


//댓글 추가
   const handleAddComment = () => {
    if (newComment.trim() === "") return;
    const newOne: CommentType = {
      id: Date.now(),
      postId: post!.id,
      content: newComment,
      username: "현재유저",
      createdAt: new Date().toISOString(),
    };
    setComments([...comments, newOne]);
    setNewComment("");
  };

  //댓글 삭제
  const handleDeleteComment = (id: number) => {
    setComments(comments.filter((c) => c.id !== id));
  };

  //댓글 수정
  const handleEditComment = (id: number, content: string) => {
    setEditingCommentId(id);
    setEditedCommentContent(content);
  };

  //댓글 저장
  const handleSaveComment = () => {
    setComments((prev) =>
      prev.map((c) =>
        c.id === editingCommentId ? { ...c, content: editedCommentContent } : c
      )
    );
    setEditingCommentId(null);
    setEditedCommentContent("");
  };

  //댓글 수정 취소
  const handleCancelCommentEdit = () => {
    setEditingCommentId(null);
    setEditedCommentContent("");
  };

  useEffect(() => {
    fetchPost();
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
            작성자: {post.username} | 작성일: {post.createdAt.split("T")[0]} | 조회수: {post.viewCount}
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

           {!editMode && (
            <div className="flex gap-4">
              <Button 
                onClick={() => setEditMode(true)}
                className="bg-blue-500 hover:bg-blue-600 text-white">
                수정
              </Button>
          
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
            <div
              key={comment.id}
              className="mb-4 p-4 bg-gray-800 rounded-md flex flex-col gap-2"
            >
              <div className="text-sm text-gray-400">
                {comment.username} | {comment.createdAt.split("T")[0]}
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
                  <div className="flex gap-2">
                    <Button
                      onClick={() => handleEditComment(comment.id, comment.content)}
                      className="bg-blue-500 hover:bg-blue-600 text-white"
                    >
                      수정
                    </Button>
                    <Button
                      onClick={() => handleDeleteComment(comment.id)}
                      className="bg-gray-500 hover:bg-gray-600 text-white"
                    >
                      삭제
                    </Button>
                  </div>
                </>
              )}
            </div>
          ))}

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
        </div>
      </main>
      <Footer />
    </div>
  );
};

export default PostDetail;
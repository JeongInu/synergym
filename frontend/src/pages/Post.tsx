
import { useState, useEffect } from "react";
// React 컴포넌트에서 상태 관리와 사이드 이펙트를 처리하기 위한 Hook 불러오기
import { useNavigate } from "react-router-dom";
// 페이지 이동(라우팅)을 위한 React Router의 Hook 불러오기
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import api from "../api/axiosInstance"; 

import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"

import {
  Pagination,
  PaginationContent,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination"

import { useUserStore } from "@/store/userStore";


// 게시글 타입 정의
interface PostType {
  id: number;
  title: string;
  content: string;
  userId: number;
  username: string; // 작성자 이름
  createdAt: string; // 생성일
  updatedAt: string; // 수정일
  viewCount: number; // 조회수
  deleteYn: boolean; // 삭제 여부
}

// 컴포넌트 정의
const Post = () => {

  // 상태 정의
  const [posts, setPosts] = useState<PostType[]>([]);
  const [filteredPosts, setFilteredPosts] = useState<PostType[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(1);
  const [keyword, setKeyword] = useState<string>("");
  
  const navigate = useNavigate();
  const postsPerPage = 10; // 페이지당 게시글 수



// 게시글 불러오기 (백엔드 연동)
  const fetchPosts = async () => {
    setLoading(true);
    try {
      // 백엔드에서 전체 게시물 목록을 받아옴
      const response = await api.get("/posts"); // /posts 엔드포인트 사용 가정
      // 받아온 전체 게시물 저장
      setPosts(response.data);
      // 초기에는 전체 게시물이 필터링된 게시물 목록
      setFilteredPosts(response.data);
      setTotalPages(Math.ceil(response.data.length / postsPerPage));
    } catch (err) {
      setError("게시글을 불러오는데 실패했습니다.");
      console.error("Error:", err);
    } finally {
      setLoading(false);
    }
  };



// 검색 기능 (프론트엔드에서 필터링)
  const handleSearch = () => {
    const lowerCaseKeyword = keyword.toLowerCase();
    const filtered = posts.filter(
      (post) =>
        post.title.toLowerCase().includes(lowerCaseKeyword) ||
        post.content.toLowerCase().includes(lowerCaseKeyword)
    );
    setFilteredPosts(filtered);
    setCurrentPage(1); // 검색 후 첫 페이지로 이동
  };


  // 게시글 상세 페이지로 이동
  const handlePostClick = (postId: number) => {
    navigate(`/posts/${postId}`);
  };

  const user = useUserStore((state) => state.user);

  // 새 게시글 작성 페이지로 이동
  const handleWritePost = () => {
  if (!user) {
    alert("로그인 시 이용 가능합니다.");
    return;
  }
  navigate('/posts/write');
};

  // 페이지네이션 처리
  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  // 현재 페이지에 표시할 게시글 (필터링된 목록에서 선택)
  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost = indexOfLastPost - postsPerPage;
  const currentPosts = filteredPosts.slice(indexOfFirstPost, indexOfLastPost);


   // 컴포넌트 마운트 시 게시글 로드 및 검색어 변경 시 검색
  useEffect(() => {
    fetchPosts();
  }, []); // 최초 마운트 시에만 전체 게시글 로드


  // 검색 버튼 클릭 또는 Enter 키 입력 시 검색 실행
  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      handleSearch();
    }
  };


  // 날짜 형식 변환 (2025-05-13T12:34:56 -> 2025-05-13)
  const formatDate = (dateString: string) => {
    return dateString ? dateString.split("T")[0] : "";
  };


  // 렌더링
  return (
    <div className="bg-black text-white min-h-screen">
    
      <Header />
      <br/>
      <br/>
      <br/>
        <main className="container mx-auto px-4 py-6">
        <div className="container mx-auto py-6 px-4">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold">커뮤니티</h1>
          <Button 
            onClick={handleWritePost}
            className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md">
                글쓰기
            </Button>
        </div>
        
        {/* 검색 영역 */}
        <div className="flex gap-4 mb-6">
          <Input
            type="text"
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
            onKeyDown={handleKeyDown} // 이 부분 추가
            className="border border-gray-300 rounded-l px-4 py-2 w-350 focus:outline-none focus:ring-2 focus:ring-gray-500"
            placeholder="제목 또는 내용으로 검색"
            />
        <Button
              onClick={handleSearch} // 검색 버튼에 핸들러 연결
              className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md"
            >
            검색
          </Button>
        </div>
        
        {loading ? (
          <div className="text-center py-8">로딩 중...</div>
        ) : error ? (
          <div className="text-center py-8 text-red-500">{error}</div>
        ) : (
          <>
            {/* 게시글 목록 테이블 */}
            <Table>
              <TableCaption>총 게시글: {posts.length}개</TableCaption>
              <TableHeader>
                <TableRow>
                  <TableHead className="w-16 text-center">번호</TableHead>
                  <TableHead>제목</TableHead>
                  <TableHead className="w-24">작성자</TableHead>
                  <TableHead className="w-28">작성일</TableHead>
                  <TableHead className="w-20 text-center">조회수</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {currentPosts.length > 0 ? (
                  currentPosts.map((post) => (
                    <TableRow 
                      key={post.id} 
                      className="hover:bg-gray-50 hover:text-black cursor-pointer"
                      onClick={() => handlePostClick(post.id)}
                    >
                      {/* 테이블 셀에 데이터 바인딩 */}
                        <TableCell className="text-center font-medium">
                          {post.id}
                        </TableCell>
                        <TableCell>{post.title}</TableCell>
                        <TableCell>{post.username}</TableCell>{" "}
                        {/* 백엔드에서 받은 username 표시 */}
                        <TableCell>{formatDate(post.createdAt)}</TableCell>
                        <TableCell className="text-center">
                          {post.viewCount}
                        </TableCell>
                      </TableRow>
                    ))
                ) : (
                  <TableRow>
                    <TableCell colSpan={5} className="text-center py-4">
                      게시글이 없습니다.
                    </TableCell>
                  </TableRow>
                )}
              </TableBody>
            </Table>
            
            {/* 페이지네이션 */}
            {totalPages > 1 && (
              <div className="mt-6">
                <Pagination>
                  <PaginationContent>
                    <PaginationItem>
                      <PaginationPrevious 
                        href="#" 
                        className="hover:bg-gray-50 hover:text-black transition-colors duration-200 rounded-md px-3 py-1"
                        onClick={(e) => {
                          e.preventDefault();
                          if (currentPage > 1) handlePageChange(currentPage - 1);
                        }}
                      />
                    </PaginationItem>
                    
                    {/* 페이지 번호 렌더링 로직 */}
                      {Array.from(
                        { length: Math.min(5, totalPages) },
                        (_, i) => {
                          let pageNum;
                          if (totalPages <= 5) {
                            pageNum = i + 1;
                          } else if (currentPage <= 3) {
                            pageNum = i + 1;
                          } else if (currentPage >= totalPages - 2) {
                            pageNum = totalPages - 4 + i;
                          } else {
                            pageNum = currentPage - 2 + i;
                          }

                          // 유효한 페이지 번호인지 확인 (총 페이지 수를 넘지 않도록)
                          if (pageNum > 0 && pageNum <= totalPages) {
                            return (
                              <PaginationItem key={pageNum}>
                                <PaginationLink
                                  href="#"
                                  isActive={currentPage === pageNum}
                                  className="hover:bg-gray-50 hover:text-black transition-colors duration-200 rounded-md px-3 py-1"
                                  onClick={(e) => {
                                    e.preventDefault();
                                    handlePageChange(pageNum);
                                  }}
                                >
                                  {pageNum}
                                </PaginationLink>
                              </PaginationItem>
                            );
                          }
                          return null; // 유효하지 않은 페이지 번호는 렌더링하지 않음
                        }
                      )}

                    {/* 더 많은 페이지가 있을 때 ... 표시 */}
                    {totalPages > 5 && currentPage < totalPages - 2 && (
                      <PaginationItem>
                        <span className="px-2">...</span>
                      </PaginationItem>
                    )}
                    
                    <PaginationItem>
                      <PaginationNext 
                        href="#" 
                        className="hover:bg-gray-50 hover:text-black transition-colors duration-200 rounded-md px-3 py-1"
                        onClick={(e) => {
                          e.preventDefault();
                          if (currentPage < totalPages)
                            handlePageChange(currentPage + 1);
                        }}
                      />
                    </PaginationItem>
                  </PaginationContent>
                </Pagination>
              </div>
            )}
          </>
        )}
      </div>
      </main>
      <Footer />
    </div>
  );
};

export default Post;


import { useState, useEffect } from "react";
// React 컴포넌트에서 상태 관리와 사이드 이펙트를 처리하기 위한 Hook 불러오기
import { useNavigate } from "react-router-dom";
// 페이지 이동(라우팅)을 위한 React Router의 Hook 불러오기
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

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
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(1);
  const [keyword, setKeyword] = useState<string>("");
  
  const navigate = useNavigate();
  const postsPerPage = 10; // 페이지당 게시글 수

   // 게시글 불러오기 (mock 데이터 사용 중)
  const fetchPosts = async () => {
    setLoading(true);
    try {
    // 실제 서버 대신 mock 데이터 사용
    const mockData: PostType[] = Array.from({ length: 25 }, (_, i) => ({
      id: i + 1,
      title: `게시글 제목 ${i + 1}`,
      content: `게시글 내용 ${i + 1}`,
      userId: i % 5,
      username: `사용자${(i % 5) + 1}`,
      createdAt: '2025-05-13T12:34:56',
      updatedAt: '2025-05-13T12:34:56',
      viewCount: Math.floor(Math.random() * 100),
      deleteYn: false,
    }));

    setPosts(mockData);
    setTotalPages(Math.ceil(mockData.length / postsPerPage));
  } catch (err) {
    setError('게시글을 불러오는데 실패했습니다.');
    console.error('Error:', err);
  } finally {
    setLoading(false);
  }
};

  // 검색 기능
  const handleSearch = async () => {
    if (!keyword.trim()) {
      fetchPosts();
      return;
    }
    
    setLoading(true);
  try {
    const filtered = posts.filter((post) =>
      post.title.includes(keyword) || post.content.includes(keyword)
    );
    setPosts(filtered);
    setTotalPages(Math.ceil(filtered.length / postsPerPage));
  } catch (err) {
    setError('검색 중 오류가 발생했습니다.');
    console.error('Search error:', err);
  } finally {
    setLoading(false);
  }
};

  // 게시글 상세 페이지로 이동
  const handlePostClick = (postId: number) => {
    navigate(`/post/${postId}`);
  };

  // 새 게시글 작성 페이지로 이동
  const handleWritePost = () => {
    navigate('/post/write');
  };

  // 페이지네이션 처리
  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  // 현재 페이지에 표시할 게시글
  const currentPosts = posts.slice(
    (currentPage - 1) * postsPerPage,
    currentPage * postsPerPage
  );

  // 컴포넌트 마운트 시 게시글 로드
  useEffect(() => {
    fetchPosts();
  }, []);

  // 날짜 형식 변환 (2025-05-13T12:34:56 -> 2025-05-13)
  const formatDate = (dateString: string) => {
    return dateString.split('T')[0];
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
            className="border border-gray-300 rounded-l px-4 py-2 w-350 focus:outline-none focus:ring-2 focus:ring-gray-500"
            placeholder="제목 또는 내용으로 검색"
            />
        <Button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md">
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
                      <TableCell className="text-center font-medium">{post.id}</TableCell>
                      <TableCell>{post.title}</TableCell>
                      <TableCell>{post.username}</TableCell>
                      <TableCell>{formatDate(post.createdAt)}</TableCell>
                      <TableCell className="text-center">{post.viewCount}</TableCell>
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
                    
                    {Array.from({ length: Math.min(5, totalPages) }, (_, i) => {
                      // 현재 페이지 중심으로 5개의 페이지 표시
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
                    })}
                    
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
                          if (currentPage < totalPages) handlePageChange(currentPage + 1);
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
import axios from './axiosInstance';

// PostDTO 타입 정의 (백엔드 구조에 맞춤)
export interface PostDTO {
    updatedAt: string;
    createdAt: string;
    id: number;
    title: string;
    content: string;
    userId: number;
    username: string;
    viewCount: number;
}

// 게시물 목록 조회 (GET /api/posts)
export const getAllPosts = async (): Promise<PostDTO[]> => {
    const response = await axios.get('/posts');
    return response.data;
};

// 특정 ID로 게시물 조회 (GET /api/posts/{id})
export const getPostById = async (id: number): Promise<PostDTO> => {
    const response = await axios.get(`/posts/${id}`);
    return response.data;
};

// 키워드로 게시물 검색 (GET /api/search?keyword={keyword})
export const searchPosts = async (keyword: string): Promise<PostDTO[]> => {
    const response = await axios.get('/search', {
        params: { keyword }
    });
    return response.data;
};

// 새로운 게시물 생성 (POST /api/posts)
export const createPost = async (postDTO: Omit<PostDTO, 'id' | 'username' | 'viewCount'>, userId: number): Promise<number> => {
    const response = await axios.post('/posts', postDTO, {
        params: { userId } // userId를 쿼리 파라미터로 전달
    });
    return response.data; // 생성된 게시물의 ID 반환
};

// 게시물 정보 수정 (PUT /api/posts/{id})
export const updatePost = async (id: number, postDTO: Partial<PostDTO>, userId: number) => {
    await axios.put(`/posts/${id}`, postDTO, { params: { userId } });
};

// 게시물 삭제 (소프트 딜리트) (DELETE /api/posts/{id})
export const deletePost = async (id: number): Promise<void> => {
    await axios.delete(`/posts/${id}`);
};


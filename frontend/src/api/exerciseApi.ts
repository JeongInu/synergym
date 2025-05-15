import axios from './axiosInstance';

interface Exercise {
    id: number;
    name: string;
    description: string;
    categoryId: number;
    categoryName: string;
    language: string;
    languageName: string;
}

// interface SpringDataPage<T> {
//     content: T[];
//     empty: boolean;
//     first: boolean;
//     last: boolean;
//     number: number;
//     numberOfElements: number;
//     pageable: any; // 또는 더 구체적인 타입 정의
//     size: number;
//     sort: any; // 또는 더 구체적인 타입 정의
//     totalElements: number;
//     totalPages: number;
// }

export const fetchExercises = async (category: string, languageCode: string, keyword: string): Promise<Exercise[]> => {
    try {
        const params: { category?: string; languageName?: string; keyword?: string } = {};
        let path = '/exercises';

        if (category) {
            params.category = category;
        }
        if (languageCode) {
            params.languageName = languageCode;
        }
        if (keyword && keyword.trim() !== '') {
            params.keyword = keyword.trim();
            path = '/exercises/search';
        } else if (category || languageCode) {
             path = '/exercises/filter'; 
        }

        console.log(`API 호출: ${path} (params: ${JSON.stringify(params)})`);
        const response = await axios.get(path, { params });

        console.log('API 응답 데이터:', response.data);

        if (Array.isArray(response.data)) {
            return response.data;
        } else if (response.data && typeof response.data === 'object' && Array.isArray(response.data.content)) {
            return response.data.content;
        } else if (response.data && typeof response.data === 'object' && Array.isArray(response.data.exercises)) {
            return response.data.exercises;
        } else {
        console.warn('API 응답 형식이 예상과 다릅니다. 알 수 없는 형태:', response.data);
        return []; // 빈 배열 반환 또는 오류 발생
    }
    } catch (error) {
        console.error(`API 에러 - 조회 실패:`, error);
        throw error;
    }
};
import axios from './axiosInstance';

export const fetchExercises = async (category: string, language: string, keyword: string, useFilter: Boolean, useSearch: Boolean) => {
  try {
    const params: { category?: string; languageName?: string; keyword?: string } = {};
    if (category) params.category = category;
    if (language) params.languageName = language;
    if (keyword && keyword.trim() !== '') params.keyword = keyword.trim();

    let path;
    if (useSearch) {
      path = '/exercises/search';
    } else {
      path = useFilter ? '/exercises/filter' : '/exercises';
    }

    const response = await axios.get(path, { params });

    if (Array.isArray(response.data)) {
      return response.data;
    } else if (response.data && Array.isArray(response.data.exercises)) {
      return response.data.exercises;
    } else {
      return [];
    }
  } catch (error) {
    console.error(`API 에러 - ${useSearch ? '/exercises/search' : useFilter ? '/exercises/filter' : '/exercises'} 조회 실패:`, error);
    throw error;
  }
};
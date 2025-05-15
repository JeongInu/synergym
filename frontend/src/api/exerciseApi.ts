import axios from './axiosInstance';

// Fetch exercises based on category, language, and keyword
export const fetchExercises = async (category: string, language: string, keyword: string) => {
  try {
    const response = await axios.get('/exercises', {
      params: { category, language, keyword },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching exercises:', error);
    throw error;
  }
};

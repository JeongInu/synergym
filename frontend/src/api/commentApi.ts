import axios from './axiosInstance';

export interface CommentDTO {
    id: number;
    content: string;
    userId: number;
    username: string;
    postId: number;
    createdAt: string;
    updatedAt: string;
}

export const getCommentsByPostId = async (postId: number) => {
  const res = await axios.get(`/comments/post/${postId}`);
  return res.data;
};

export const addComment = async (comment: { content: string; postId: number; userId: number }) => {
  const res = await axios.post("/comments", comment);
  return res.data;
};

export const updateComment = async (id: number, comment: { content: string; userId: number }) => {
  await axios.put(`/comments/${id}`, comment);
};

export const deleteComment = async (id: number, userId: number) => {
  await axios.delete(`/comments/${id}`, { params: { userId } });
};



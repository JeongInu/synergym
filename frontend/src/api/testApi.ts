import axios from './axiosInstance';

export const testServer = async() => {
    const response = await axios.get('/test');
    return response.data;
}
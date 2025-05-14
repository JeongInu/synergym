import axios from './axiosInstance';

interface LoginRequest {
    email: string;
    password: string;
}

interface LoginResponse {
    id: number;
    email: string;
    username: string;
    token: string;
    role: string;
    success: boolean;
    message: string;
}

interface JoinForm {
    email: string;
    password: string;
    confirm: string;
    username: string;
    birth: {
        year: string;
        month: string;
        day: string;
    };
    gender: string;
    weight: string;
    height: string;
    activity: string;
    goal: string;
}

export const login = async(data: LoginRequest): Promise<LoginResponse> => {
    console.log(data);
    const response = await axios.post<LoginResponse>('/auth/login', data);
    return response.data;
}

export const joinUser = async(form: JoinForm) => {

    const payload = {
        email: form.email,
        password: form.password,
        name: form.username,  // name으로 변경
        birthDate: `${form.birth.year}-${form.birth.month.padStart(2, "0")}-${form.birth.day.padStart(2, "0")}`,  // birthDate로 변경
        gender: form.gender,
        weight: parseFloat(form.weight),
        height: parseFloat(form.height),
        fitnessLevel: form.activity, // activity -> fitnessLevel
        userGoal: form.goal          // goal -> userGoal
    };

    return axios.post("/auth/signup", payload);
}
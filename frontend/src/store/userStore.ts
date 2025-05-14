import { create } from 'zustand';
import { persist } from 'zustand/middleware';

export interface User{
    id: number;
    email: string;
    username: string;
    token: string;
    role: string;
    success: boolean;
    message: string
}

interface UserStore {
    user: User | null;
    setUser: (userData: User) => void;
    clearUser: () => void;
    clearUserStorage: () => void;
}

export const useUserStore = create<UserStore>()(
  persist(
    (set) => ({
      user: null,
      setUser: (userData) => set({ user: userData }),
      clearUser: () => set({ user: null }),
      clearUserStorage: () => {
          localStorage.removeItem('user-storage');
      },
    }),
    {
      name: 'user-storage', // localStorage에 저장되는 key
    }
  )
);
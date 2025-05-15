import { useState } from 'react';
import {
  Card,
  CardContent,
  CardHeader,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";
import { fetchExercises } from '@/api/exerciseApi';

// Define the type for exercises
interface Exercise {
  name: string;
  description: string;
  category: string;
  language: string;
}

const ExerciseList = () => {
  const [category, setCategory] = useState('');
  const [language, setLanguage] = useState('');
  const [keyword, setKeyword] = useState('');
  const [exercises, setExercises] = useState<Exercise[]>([]);

  const handleSearch = async () => {
    try {
      console.log('검색 요청 데이터:', { category, language, keyword });
      const data = await fetchExercises(category, language, keyword);
      console.log('검색 응답 데이터:', data);
      setExercises(data);
    } catch (error) {
      console.error('Failed to fetch exercises:', error);
    }
  };

  const handleCategoryChange = async (value: string) => {
    setCategory(value);
    try {
      console.log('카테고리 선택 데이터:', { category: value, language, keyword });
      const data = await fetchExercises(value, language, keyword);
      console.log('카테고리 선택 응답 데이터:', data);
      setExercises(data);
    } catch (error) {
      console.error('Failed to fetch exercises on category change:', error);
    }
  };

  return (
    <div className="bg-black text-white min-h-screen mx-auto p-4">
      <Header />
      <br />
      <br />
      <br />
      <br />
      <h1 className="text-2xl font-bold mb-4">운동 목록</h1>

      {/* 검색 UI */}
      <div className="mb-4 flex gap-4">
        <Select onValueChange={handleCategoryChange}>
          <SelectTrigger className="w-40">
            <SelectValue placeholder="카테고리 선택" />
          </SelectTrigger>
          <SelectContent className="bg-white text-black">
            <SelectItem value="8">팔</SelectItem>
            <SelectItem value="9">다리</SelectItem>
            <SelectItem value="10">복근</SelectItem>
            <SelectItem value="11">가슴</SelectItem>
            <SelectItem value="12">등</SelectItem>
            <SelectItem value="13">어깨</SelectItem>
            <SelectItem value="14">종아리</SelectItem>
            <SelectItem value="15">유산소</SelectItem>
          </SelectContent>
        </Select>

        <Select onValueChange={(value) => setLanguage(value)} defaultValue="EN">
          <SelectTrigger className="w-40">
            <SelectValue placeholder="언어 선택" />
          </SelectTrigger>
          <SelectContent className="bg-white text-black max-h-50 overflow-y-auto">
            <SelectItem value="AZ">아제르바이잔어</SelectItem>
            <SelectItem value="ID">인도네시아어</SelectItem>
            <SelectItem value="DE">독일어</SelectItem>
            <SelectItem value="EN">영어</SelectItem>
            <SelectItem value="ES">스페인어</SelectItem>
            <SelectItem value="EO">에스페란토</SelectItem>
            <SelectItem value="FR">프랑스어</SelectItem>
            <SelectItem value="HR">크로아티아어</SelectItem>
            <SelectItem value="IT">이탈리아어</SelectItem>
            <SelectItem value="NL">네덜란드어</SelectItem>
            <SelectItem value="NO">노르웨이어</SelectItem>
            <SelectItem value="PL">폴란드어</SelectItem>
            <SelectItem value="PT">포르투갈어</SelectItem>
            <SelectItem value="SV">스웨덴어</SelectItem>
            <SelectItem value="TR">터키어</SelectItem>
            <SelectItem value="CS">체코어</SelectItem>
            <SelectItem value="EL">그리스어</SelectItem>
            <SelectItem value="BG">불가리아어</SelectItem>
            <SelectItem value="RU">러시아어</SelectItem>
            <SelectItem value="UK">우크라이나어</SelectItem>
          </SelectContent>
        </Select>

        <Input
          type="text"
          placeholder="검색어 입력"
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          className="w-full max-w-md"
        />

        <Button onClick={handleSearch} className="bg-blue-600 text-white">
          검색
        </Button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {exercises.length > 0 ? (
          exercises.map((exercise, index) => (
            <Card key={index}>
              <CardHeader>
                <h2 className="text-lg font-semibold">{exercise.name}</h2>
              </CardHeader>
              <CardContent>
                <p>{exercise.description}</p>
              </CardContent>
            </Card>
          ))
        ) : (
          <p>검색 결과가 없습니다.</p>
        )}
      </div>
      <Footer />
    </div>
  );
};

export default ExerciseList;

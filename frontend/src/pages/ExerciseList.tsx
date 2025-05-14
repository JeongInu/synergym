import { useState } from 'react';
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import Header from "../components/common/Header";
import Footer from "../components/common/Footer";

const ExerciseList = () => {
  const [category, setCategory] = useState('');
  const [language, setLanguage] = useState('');
  const [keyword, setKeyword] = useState('');

  const handleSearch = () => {
    // 검색 버튼 클릭 시 호출될 함수
    console.log('검색:', { category, language, keyword });
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
        <Select onValueChange={(value) => setCategory(value)}>
          <SelectTrigger className="w-40">
            <SelectValue placeholder="카테고리 선택" />
          </SelectTrigger>
          <SelectContent className="bg-white text-black">
            <SelectItem value="1">카테고리 1</SelectItem>
            <SelectItem value="2">카테고리 2</SelectItem>
            {/* 추가 카테고리 옵션 */}
          </SelectContent>
        </Select>

        <Select onValueChange={(value) => setLanguage(value)}>
          <SelectTrigger className="w-40">
            <SelectValue placeholder="언어 선택" />
          </SelectTrigger>
          <SelectContent className="bg-white text-black">
            <SelectItem value="ko">한국어</SelectItem>
            <SelectItem value="en">영어</SelectItem>
            {/* 추가 언어 옵션 */}
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
        {/* 운동 카드 예시 */}
        <Card>
          <CardHeader>
            <h2 className="text-lg font-semibold">운동 이름</h2>
          </CardHeader>
          <CardContent>
            <p>운동 설명</p>
          </CardContent>
        </Card>
        {/* 추가 운동 카드들 */}
      </div>
      <Footer />
    </div>
  );
};

export default ExerciseList;

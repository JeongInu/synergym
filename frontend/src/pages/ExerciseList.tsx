import { useCallback, useEffect, useState } from "react";
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

interface Exercise {
  id: number;
  name: string;
  description: string;
  categoryId: number;
  categoryName: string;
  language: string;
  languageName: string;
}

const ExerciseList = () => {
  const [category, setCategory] = useState('');
  const [language, setLanguage] = useState('');
  const [keyword, setKeyword] = useState('');
  const [useFilter, setUseFilter] = useState(false); // 필터 사용 여부
  const [useSearch, setUseSearch] = useState(false); // 검색 사용 여부
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const loadExercises = useCallback(async () => {
    setLoading(true);
    setError(null);
    console.log('데이터 가져오기 시도:', { category, languageName: language, keyword, useFilter, useSearch }); // language를 languageName으로 사용
    try {
      const data = await fetchExercises(category, language, keyword);
      console.log('데이터 가져오기 결과:', data);
      setExercises(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error('운동 데이터를 불러오는 데 실패했습니다:', err);
      setError("운동 데이터를 불러오는 데 실패했습니다.");
      setExercises([]);
    } finally {
      setLoading(false);
    }
  }, [category, language, keyword]); 

  useEffect(() => {
    if (!useSearch) {
      loadExercises();
    }
  }, [category, language, useFilter]);

  // 검색 버튼 클릭 핸들러
  const handleSearch = () => {
    setKeyword(keyword.trim());
    setUseSearch(true);
    loadExercises();
    console.log('검색 버튼 클릭:', { category, languageName: language, keyword });
  };

  // 카테고리 변경 핸들러
  const handleCategoryChange = (value: string) => {
    setUseFilter(true); // 필터 사용 여부를 true로 설정
    console.log('카테고리 변경:', value);
    setCategory(value); // 상태만 변경, 데이터 로딩은 useEffect가 처리
  };

  // 언어 변경 핸들러
  const handleLanguageChange = (value: string) => {
    setUseFilter(true); // 필터 사용 여부를 true로 설정
    console.log('언어 변경:', value);
    setLanguage(value); // 상태만 변경, 데이터 로딩은 useEffect가 처리
  };

  if (loading) return <p className="text-white text-center py-10">로딩중...</p>;
  if (error) return <p className="text-red-500 text-center py-10">{error}</p>;

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
        <Select onValueChange={handleCategoryChange} defaultValue={category}>
          <SelectTrigger className="w-40">
            <SelectValue placeholder="카테고리 선택" />
          </SelectTrigger>
          <SelectContent className="bg-white text-black">
            <SelectItem value="Arms">팔</SelectItem>
            <SelectItem value="Legs">다리</SelectItem>
            <SelectItem value="Abs">복근</SelectItem>
            <SelectItem value="Chest">가슴</SelectItem>
            <SelectItem value="Back">등</SelectItem>
            <SelectItem value="Shoulders">어깨</SelectItem>
            <SelectItem value="Calves">종아리</SelectItem>
            <SelectItem value="Cardio">유산소</SelectItem>
          </SelectContent>
        </Select>

        <Select onValueChange={handleLanguageChange} defaultValue={language}>
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
          exercises.map((exercise) => (
            <Card key={exercise.id}>
              <CardHeader>
                <h2 className="text-lg font-semibold">{exercise.name}</h2>
              </CardHeader>
              <CardContent>
                <p className="text-sm text-gray-500 font-semibold">카테고리: {exercise.categoryName}</p>
                <p className="text-sm text-gray-500 font-semibold">언어: {exercise.languageName}</p>
                <br></br>
                <div dangerouslySetInnerHTML={{ __html: exercise.description }} />
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

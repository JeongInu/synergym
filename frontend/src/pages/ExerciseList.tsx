import React from 'react';
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"

const ExerciseList = () => {
  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">운동 목록</h1>
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
    </div>
  );
};

export default ExerciseList;

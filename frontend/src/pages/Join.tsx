// components/SignupForm.tsx
import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { motion } from "framer-motion";

const variants = {
  hidden: { opacity: 0, y: -10 },
  visible: { opacity: 1, y: 0 },
};

export default function Join() {
  const [form, setForm] = useState({
    email: "",
    password: "",
    confirm: "",
    name: "",
    birth: { year: "", month: "", day: "" },
    gender: "",
    weight: "",
    height: "",
    activity: "",
    goal: "",
  });

  const handleChange = (field: string, value: any) => {
    setForm((prev) => ({ ...prev, [field]: value }));
  };

  const allVisible = {
    showPassword: form.email !== "",
    showConfirm: form.password !== "",
    showName: form.confirm !== "",
    showBirth: form.name !== "",
    showGender: form.birth.year && form.birth.month && form.birth.day,
    showWeight: form.gender !== "",
    showHeight: form.weight !== "",
    showActivity: form.height !== "",
    showGoal: form.activity !== "",
    showSubmit: form.goal !== "",
  };

  const years = Array.from({ length: 100 }, (_, i) => `${2025 - i}`);
  const months = Array.from({ length: 12 }, (_, i) => `${i + 1}`);
  const days = Array.from({ length: 31 }, (_, i) => `${i + 1}`);

  return (
    <div className="min-h-screen bg-black text-white flex justify-center items-center px-4">
      <div className="w-full max-w-lg space-y-4 p-8 bg-neutral-900 rounded-xl shadow-lg">
        <h1 className="text-3xl font-bold text-center mb-4">회원가입</h1>

        <Input
          placeholder="이메일"
          className="bg-neutral-800 text-white"
          value={form.email}
          onChange={(e) => handleChange("email", e.target.value)}
        />

        {allVisible.showPassword && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Input
              placeholder="비밀번호"
              type="password"
              className="bg-neutral-800 mt-2"
              value={form.password}
              onChange={(e) => handleChange("password", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showConfirm && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Input
              placeholder="비밀번호 확인"
              type="password"
              className="bg-neutral-800 mt-2"
              value={form.confirm}
              onChange={(e) => handleChange("confirm", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showName && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Input
              placeholder="회원 이름"
              className="bg-neutral-800 mt-2"
              value={form.name}
              onChange={(e) => handleChange("name", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showBirth && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <div className="flex gap-2 mt-2">
              <select
                className="flex-1 bg-neutral-800 text-white p-2 rounded"
                value={form.birth.year}
                onChange={(e) => setForm({ ...form, birth: { ...form.birth, year: e.target.value } })}
              >
                <option value="">년</option>
                {years.map((y) => <option key={y}>{y}</option>)}
              </select>
              <select
                className="flex-1 bg-neutral-800 text-white p-2 rounded"
                value={form.birth.month}
                onChange={(e) => setForm({ ...form, birth: { ...form.birth, month: e.target.value } })}
              >
                <option value="">월</option>
                {months.map((m) => <option key={m}>{m}</option>)}
              </select>
              <select
                className="flex-1 bg-neutral-800 text-white p-2 rounded"
                value={form.birth.day}
                onChange={(e) => setForm({ ...form, birth: { ...form.birth, day: e.target.value } })}
              >
                <option value="">일</option>
                {days.map((d) => <option key={d}>{d}</option>)}
              </select>
            </div>
          </motion.div>
        )}

        {allVisible.showGender && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <div className="mt-2 space-x-4">
              <label>
                <input
                  type="radio"
                  value="남"
                  checked={form.gender === "남"}
                  onChange={(e) => handleChange("gender", e.target.value)}
                /> 남
              </label>
              <label>
                <input
                  type="radio"
                  value="여"
                  checked={form.gender === "여"}
                  onChange={(e) => handleChange("gender", e.target.value)}
                /> 여
              </label>
            </div>
          </motion.div>
        )}

        {allVisible.showWeight && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Input
              placeholder="몸무게 (kg)"
              className="bg-neutral-800 mt-2"
              type="number"
              value={form.weight}
              onChange={(e) => handleChange("weight", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showHeight && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Input
              placeholder="키 (cm)"
              className="bg-neutral-800 mt-2"
              type="number"
              value={form.height}
              onChange={(e) => handleChange("height", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showActivity && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <select
              className="mt-2 w-full bg-neutral-800 text-white p-2 rounded"
              value={form.activity}
              onChange={(e) => handleChange("activity", e.target.value)}
            >
              <option value="">활동량 선택</option>
              <option value="주0회">주 0회</option>
              <option value="주1-2회">주 1-2회</option>
              <option value="주3-4회">주 3-4회</option>
              <option value="주5-6회">주 5-6회</option>
            </select>
          </motion.div>
        )}

        {allVisible.showGoal && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <select
              className="mt-2 w-full bg-neutral-800 text-white p-2 rounded"
              value={form.goal}
              onChange={(e) => handleChange("goal", e.target.value)}
            >
              <option value="">운동 목표 선택</option>
              <option value="체중감량">체중감량</option>
              <option value="근력상승">근력상승</option>
              <option value="기초체력">기초체력</option>
              <option value="자세교정">자세교정</option>
              <option value="유연성">유연성</option>
              <option value="기타">기타</option>
            </select>
          </motion.div>
        )}

        {allVisible.showSubmit && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Button className="w-full mt-4 bg-white text-black hover:bg-gray-200">가입 완료</Button>
          </motion.div>
        )}
      </div>
    </div>
  );
}

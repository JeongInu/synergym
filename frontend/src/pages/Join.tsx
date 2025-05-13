import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { motion } from "framer-motion";
import DateSelector from "@/components/common/DateSelector";

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

  const handleChange = (field: string, value: string) => {
    setForm((prev) => ({ ...prev, [field]: value }));
  };

  const handleBirthChange = (type: "year" | "month" | "day", value: string) => {
    setForm((prev) => ({
      ...prev,
      birth: {
        ...prev.birth,
        [type]: value,
      },
    }));
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

  return (
    <div className="min-h-screen bg-black text-white flex justify-center items-center px-4">
      <div className="w-full max-w-lg space-y-4 p-8 bg-neutral-900 rounded-xl shadow-lg">
        <h1 className="text-3xl font-bold text-center mb-4">회원가입</h1>

        <Label htmlFor="email" className="block mb-1">Email</Label>
        <Input
          placeholder="example@domain.com"
          id="email"
          className="bg-neutral-800 text-white"
          value={form.email}
          onChange={(e) => handleChange("email", e.target.value)}
        />

        {allVisible.showPassword && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="password" className="block mb-1">비밀번호</Label>
            <Input
              placeholder="********"
              id="password"
              type="password"
              className="bg-neutral-800 mt-2"
              value={form.password}
              onChange={(e) => handleChange("password", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showConfirm && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="confirm" className="block mb-1">비밀번호 확인</Label>
            <Input
              placeholder="********"
              id="confirm"
              type="password"
              className="bg-neutral-800 mt-2"
              value={form.confirm}
              onChange={(e) => handleChange("confirm", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showName && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="username">닉네임</Label>
            <Input
              placeholder="닉네임"
              id="userName"
              className="bg-neutral-800 mt-2"
              value={form.name}
              onChange={(e) => handleChange("name", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showBirth && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <DateSelector 
              year={form.birth.year}
              month={form.birth.month}
              day={form.birth.day}
              onChange={handleBirthChange}/>
          </motion.div>
        )}

        {allVisible.showGender && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="gender" className="block mb-1">성별</Label>
            <div className="mt-2 space-x-4" id="gender">
              <label>
                <input
                  type="radio"
                  value="male"
                  checked={form.gender === "male"}
                  onChange={(e) => handleChange("gender", e.target.value)}
                /> 남
              </label>
              <label>
                <input
                  type="radio"
                  value="female"
                  checked={form.gender === "female"}
                  onChange={(e) => handleChange("gender", e.target.value)}
                /> 여
              </label>
            </div>
          </motion.div>
        )}

        {allVisible.showWeight && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="weight" className="block mb-1">몸무게</Label>
            <Input
              id="weight"
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
            <Label htmlFor="height" className="block mb-1">신장</Label>
            <Input
              placeholder="키 (cm)"
              id="height"
              className="bg-neutral-800 mt-2"
              type="number"
              value={form.height}
              onChange={(e) => handleChange("height", e.target.value)}
            />
          </motion.div>
        )}

        {allVisible.showActivity && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="fitness_level" className="block mb-1">활동량</Label>
            <Select value={form.activity} onValueChange={(value) => handleChange("activity", value)}>
              <SelectTrigger className="mt-2 w-full bg-neutral-800 text-white p-2 rounded">
                <SelectValue placeholder="활동량 선택" />
              </SelectTrigger>
              <SelectContent className="bg-neutral-800 text-white max-h-64 overflow-y-auto">
                <SelectItem value="lazy">주 0회</SelectItem>
                <SelectItem value="normal">주 1-2회</SelectItem>
                <SelectItem value="diligent">주 3-4회</SelectItem>
                <SelectItem value="too_much">주 5-6회</SelectItem>
              </SelectContent>
            </Select>
          </motion.div>
        )}

        {allVisible.showGoal && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="goal" className="block mb-1">운동목표</Label>
            <Select value={form.goal} onValueChange={(value) => handleChange("goal", value)}>
              <SelectTrigger className="mt-2 w-full bg-neutral-800 text-white p-2 rounded">
                <SelectValue placeholder="운동 목표 선택" />
              </SelectTrigger>
              <SelectContent className="bg-neutral-800 text-white max-h-64 overflow-y-auto">
                <SelectItem value="loss_weight">체중감량</SelectItem>
                <SelectItem value="gain_muscle">근력상승</SelectItem>
                <SelectItem value="fitness">기초체력</SelectItem>
                <SelectItem value="posture">자세교정</SelectItem>
                <SelectItem value="flexibility">유연성</SelectItem>
                <SelectItem value="etc">기타</SelectItem>
              </SelectContent>
            </Select>
          </motion.div>
        )}

        {allVisible.showSubmit && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Button className="w-full mt-4 bg-white text-black hover:bg-gray-200"> 완료</Button>
          </motion.div>
        )}
      </div>
    </div>
  );
}

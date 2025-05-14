import { useState, useEffect } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { motion } from "framer-motion";
import DateSelector from "@/components/common/DateSelector";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group"

const variants = {
  hidden: { opacity: 0, y: -10 },
  visible: { opacity: 1, y: 0 },
};

export default function Join() {
  const [form, setForm] = useState({
    email: "",
    password: "",
    confirm: "",
    username: "",
    birth: { year: "", month: "", day: "" },
    gender: "",
    weight: "",
    height: "",
    activity: "",
    goal: "",
  });

  const [shown, setShown] = useState({
    password: false,
    confirm: false,
    username: false,
    birth: false,
    gender: false,
    weight: false,
    height: false,
    activity: false,
    goal: false,
  });

  useEffect(() => {
    if (form.email && !shown.password)
      setShown(prev => ({ ...prev, password: true }));
  }, [form.email, shown.password]);

  useEffect(() => {
    if (form.password && !shown.confirm)
      setShown(prev => ({ ...prev, confirm: true }));
  }, [form.password, shown.confirm]);

  useEffect(() => {
    if (form.confirm && !shown.username)
      setShown(prev => ({ ...prev, username: true }));
  }, [form.confirm, shown.username]);

  useEffect(() => {
    if (form.username && !shown.birth)
      setShown(prev => ({ ...prev, birth: true }));
  }, [form.username, shown.birth]);

  useEffect(() => {
    if (form.birth.year && form.birth.month && form.birth.day && !shown.gender)
      setShown(prev => ({ ...prev, gender: true }));
  }, [form.birth, shown.gender]);

  useEffect(() => {
    if (form.gender && !shown.weight)
      setShown(prev => ({ ...prev, weight: true }));
  }, [form.gender, shown.weight]);

  useEffect(() => {
    if (form.weight && !shown.height)
      setShown(prev => ({ ...prev, height: true }));
  }, [form.weight, shown.height]);

  useEffect(() => {
    if (form.height && !shown.activity)
      setShown(prev => ({ ...prev, activity: true }));
  }, [form.height, shown.activity]);

  useEffect(() => {
    if (form.activity && !shown.goal)
      setShown(prev => ({ ...prev, goal: true }));
  }, [form.activity, shown.goal]);

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
    showBirth: form.username !== "",
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
        <h1 className="text-3xl font-bold text-center mb-4">Sign Up</h1>

        <Label htmlFor="email" className="block mb-1">Email</Label>
        <Input
          placeholder="example@domain.com"
          id="email"
          className="bg-neutral-800 text-white"
          value={form.email}
          onChange={(e) => handleChange("email", e.target.value)}
        />

        {shown.password && (
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

        {shown.confirm && (
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

        {shown.username && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="username">닉네임</Label>
            <Input
              placeholder="닉네임"
              id="username"
              className="bg-neutral-800 mt-2"
              value={form.username}
              onChange={(e) => handleChange("username", e.target.value)}
            />
          </motion.div>
        )}

        {shown.birth && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <DateSelector 
              year={form.birth.year}
              month={form.birth.month}
              day={form.birth.day}
              onChange={handleBirthChange}/>
          </motion.div>
        )}

        {shown.gender && (
          <motion.div variants={variants} initial="hidden" animate="visible">
            <Label htmlFor="gender" className="block mb-1">성별</Label>
            <RadioGroup
              id="gender"
              defaultValue={form.gender}
              onValueChange={(value) => handleChange("gender", value)}
              className="mt-2 flex gap-4"
            >
              <div className="flex items-center space-x-2">
                <RadioGroupItem value="male" id="male" />
                <Label htmlFor="male">남</Label>
              </div>
              <div className="flex items-center space-x-2">
                <RadioGroupItem value="female" id="female" />
                <Label htmlFor="female">여</Label>
              </div>
            </RadioGroup>
          </motion.div>
        )}

        {shown.weight && (
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

        {shown.height && (
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

        {shown.activity && (
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

        {shown.goal && (
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
            <div className="w-full flex justify-center mt-8">
              <Button className="px-8 py-4 text-lg font-semibold bg-transparent border border-gray-500 text-white hover:bg-white hover:text-black transition-colors duration-300 rounded-xl">
                회원 가입
              </Button>
            </div>
          </motion.div>
        )}
      </div>
    </div>
  );
}

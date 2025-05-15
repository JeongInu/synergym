import { Sparkles, Dumbbell, Heart } from "lucide-react";
import Section2Card from "./Section2Card";
import { useScrollAnime } from "../../../hooks/useScrollAnime";
import { motion } from "framer-motion";

const cards = [
  {
    icon: <Sparkles />,
    title: "사진 자세 분석",
    description: "앉아있는 사진을 등록하면 AI가 자세에 대한 분석결과를 제공해요! 개인 맞춤형 운동 추천까지😉",
    link: "/",
  },
  {
    icon: <Dumbbell />,
    title: "커뮤니티에서 소통해요",
    description: "틀어진 자세 때문에 힘든 사람들 모여라! 다양한 정보를 공유하며 커뮤니티에서 자유롭게 소통해요🥰",
    link: "/posts",
  },
  {
    icon: <Heart />,
    title: "나의 루틴 트래킹",
    description: "추천받은 운동을 기반으로 나만의 루틴을 생성해서 꾸준히 실행해봐요! 기록이 습관을 만든답니다✅",
    link: "/",
  },
];

export default function Section2CardList() {
  const { ref, controls } = useScrollAnime();

  return (
    <motion.div
      ref={ref}
      initial="hidden"
      animate={controls}
      className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 mt-20"
    >
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8">
        {cards.map((card, idx) => (
          <Section2Card
            key={idx}
            icon={card.icon}
            title={card.title}
            description={card.description}
            link={card.link}
            index={idx}
          />
        ))}
      </div>
    </motion.div>
  );
}

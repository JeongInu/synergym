import { motion } from "framer-motion";

const cardVariants = {
  hidden: { opacity: 0, y: 70 },
  visible: (i: number) => ({
    opacity: 1,
    y: 0,
    transition: {
      delay: i * 0.4,
      duration: 1.8,
    },
  }),
};

interface Section2CardProps {
  icon: React.ReactNode;
  title: string;
  description: string;
  link: string;
  index: number;
}

export default function Section2Card({
  icon,
  title,
  description,
  link,
  index,
}: Section2CardProps) {
  return (
    <motion.div
        variants={cardVariants}
        initial="hidden"
        animate="visible"
        custom={index}
        className="bg-white/90 rounded-2xl shadow-xl p-8 min-h-[360px] flex flex-col items-center text-center space-y-6 hover:shadow-2xl transition will-change-transform"
        >
        <div className="w-16 h-16 flex items-center justify-center rounded-full bg-blue-100 text-blue-600 text-3xl">
            {icon}
        </div>
        <h3 className="text-xl font-semibold">{title}</h3>
        <p className="text-sm text-zinc-600">{description}</p>
        
        {/* 여기에 margin-top: auto를 줘서 링크를 맨 아래로 밀기 */}
        <a
            href={link}
            className="text-blue-500 hover:underline text-sm font-medium mt-auto"
        >
            자세히 알아보기 →
        </a>
    </motion.div>
  );
}

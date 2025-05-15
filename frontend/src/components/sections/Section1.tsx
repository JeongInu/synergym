import { motion } from "framer-motion";
import { useScrollAnime } from "../../hooks/useScrollAnime";
import Content from "./content/Section1Content";

const variants = {
  hidden: { opacity: 0, y: 80 },
  visible: { opacity: 1, y: 0, transition: { duration: 0.8, ease: "easeOut" } },
};

const Section1 = () => {
  const { ref, controls } = useScrollAnime();

  return (
    <motion.section
      ref={ref}
      initial="hidden"
      animate={controls}
      variants={variants}
      className="h-screen bg-black text-white flex flex-col relative"
    >
      <div className="flex flex-1">
        {/* 왼쪽: 텍스트 & 버튼 */}
        <Content />

        {/* 오른쪽: 로고 */}
        <div className="w-1/2 flex items-center justify-center">
          <img
            src="/images/synergym_logo.png"
            alt="Synergym Logo"
            className="w-72 h-auto object-contain"
          />
        </div>
      </div>
    </motion.section>
  );
};

export default Section1;

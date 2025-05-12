// src/sections/Section1.tsx
import { motion } from "framer-motion";
import { useScrollAnime } from "../../hooks/useScrollAnime";

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
      className="h-screen flex items-center justify-center bg-gradient-to-br from-red-400 to-pink-500"
    >
      <h1 className="text-white text-6xl font-bold">Section 1</h1>
    </motion.section>
  );
};

export default Section1;

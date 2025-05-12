// src/sections/Section3.tsx
import { motion } from "framer-motion";
import { useScrollAnime } from "../hooks/useScrollAnime";

const variants = {
  hidden: { opacity: 0, scale: 0.8 },
  visible: { opacity: 1, scale: 1, transition: { duration: 1, ease: "easeOut" } },
};

const Section3 = () => {
  const { ref, controls } = useScrollAnime();

  return (
    <motion.section
      ref={ref}
      initial="hidden"
      animate={controls}
      variants={variants}
      className="h-screen flex items-center justify-center bg-gradient-to-br from-green-400 to-yellow-500"
    >
      <h1 className="text-white text-6xl font-bold">Section 3</h1>
    </motion.section>
  );
};

export default Section3;

// src/sections/Section5.tsx
import { motion } from "framer-motion";
import { useScrollAnime } from "../hooks/useScrollAnime";

const variants = {
  hidden: { opacity: 0, x: -200 },
  visible: { opacity: 1, x: 0, transition: { duration: 1, ease: "easeOut" } },
};

const Section5 = () => {
  const { ref, controls } = useScrollAnime();

  return (
    <motion.section
      ref={ref}
      initial="hidden"
      animate={controls}
      variants={variants}
      className="h-screen flex items-center justify-center bg-gradient-to-br from-purple-400 to-indigo-500"
    >
      <h1 className="text-white text-6xl font-bold">Section 5</h1>
    </motion.section>
  );
};

export default Section5;

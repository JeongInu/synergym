import { motion } from "framer-motion";
import { useScrollAnime } from "@/hooks/useScrollAnime";
import Section2Header from "./content/Section2Header";
import Section2CardList from "./content/Section2CardList";

const variants = {
  hidden: { opacity: 0, y: 80 },
  visible: { opacity: 1, y: 0, transition: { duration: 0.8, ease: "easeOut" } },
};

const Section2 = () => {
  const { ref, controls } = useScrollAnime();

  return (
    <motion.section
      ref={ref}
      initial="hidden"
      animate={controls}
      variants={variants}
      className="h-screen flex flex-col items-center justify-center bg-gradient-to-br from-blue-400 to-teal-500"
    >
      <Section2Header controls={controls} />
      <Section2CardList />
    </motion.section>
  );
};

export default Section2;
import { Button } from "@/components/ui/button";
import { motion } from "framer-motion";

const Section1Content = () => {

  const handleButtonClick = () => {
    alert("안되지롱");
  };  

  return (
    <div className="w-1/2 flex flex-col justify-center px-16 space-y-6 text-white">
      
      <motion.p
        className="text-lg leading-relaxed"
        initial={{ opacity: 0, y: 50, scale: 0.8 }} 
        animate={{ opacity: 1, y: 0, scale: 1 }} 
        transition={{
          duration: 1.2,
          type: "spring",
          stiffness: 100, 
          damping: 20
        }} 
      >
        for your health
      </motion.p>

      <motion.h1
        className="text-5xl font-bold"
        initial={{ opacity: 0, y: 50, scale: 1.2 }} 
        animate={{ opacity: 1, y: 0, scale: 1 }} 
        transition={{
          duration: 1.4,
          type: "spring",
          stiffness: 120, 
          damping: 25
        }}
      >
        SYNERGYM
      </motion.h1>

      <motion.p
        className="text-lg leading-relaxed"
        initial={{ opacity: 0, y: 50, scale: 0.8 }} 
        animate={{ opacity: 1, y: 0, scale: 1 }} 
        transition={{
          duration: 1.6,
          type: "spring",
          stiffness: 120,
          damping: 25
        }}
      >
        당신만을 위한 AI PT쌤과 함께하는
      </motion.p>

      <motion.p
        className="text-lg leading-relaxed"
        initial={{ opacity: 0, y: 50, scale: 0.8 }} 
        animate={{ opacity: 1, y: 0, scale: 1 }} 
        transition={{
          duration: 1.8,
          type: "spring",
          stiffness: 120,
          damping: 25
        }}
      >
        맞춤형 바른자세 길잡이
      </motion.p>

      <Button
        onClick={handleButtonClick}
        className="w-56 h-12 bg-blue-500 text-white font-semibold rounded-lg transition-all duration-300 hover:bg-blue-600 mt-10"
      >
        사진으로 분석받기
      </Button>
    </div>
  );
};

export default Section1Content;

import { motion } from "framer-motion";
import type { AnimationControls } from "framer-motion";

interface Section2HeaderProps {
  controls: AnimationControls;
}

const headerVariants = {
  synergym: {
    hidden: { opacity: 0, y: -30 },
    visible: { opacity: 1, y: 0, transition: { duration: 0.6, ease: "easeOut" } },
  },
  subtitle: {
    hidden: { opacity: 0, y: 30 },
    visible: { opacity: 1, y: 0, transition: { duration: 0.6, ease: "easeOut", delay: 0.2 } },
  },
};

export default function Section2Header({ controls }: Section2HeaderProps) {
  return (
    <div className="text-center space-y-4">
      <motion.h1
        className="text-2xl text-white"
        initial="hidden"
        animate={controls}
        variants={headerVariants.synergym}
      >
        synergym
      </motion.h1>

      <motion.p
        className="text-4xl text-white font-bold"
        initial="hidden"
        animate={controls}
        variants={headerVariants.subtitle}
      >
        어떤 서비스인가요? 🤔
      </motion.p>
    </div>
  );
}

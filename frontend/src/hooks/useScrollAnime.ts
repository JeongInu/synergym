import { useAnimation, useInView } from "framer-motion";
import { useEffect, useRef } from "react";

export const useScrollAnime = () => {
  const ref = useRef(null);
  const controls = useAnimation();
  const inView = useInView(ref, { once: true, amount: 0.3 });

  useEffect(() => {
    if (inView) {
      controls.start("visible");
    }
  }, [inView, controls]);

  return { ref, controls, inView };
};
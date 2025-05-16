import { useState } from "react";
import ChatButton from "./ChatButton";
import ChatModal from "./ChatModal";
import TopButton from "./TopButton";

const Chatbot = () => {
  const [isChatOpen, setIsChatOpen] = useState(false);

  return (
    <>
      <ChatModal isOpen={isChatOpen} onClose={() => setIsChatOpen(false)} />
      <ChatButton onClick={() => setIsChatOpen(true)} />
      <TopButton />
    </>
  );
};

export default Chatbot;

import { useState } from "react";
import ChatButton from "./ChatButton";
import ChatModal from "./ChatModal";

const Chatbot = () => {
  const [isChatOpen, setIsChatOpen] = useState(false);

  return (
    <>
      <ChatModal isOpen={isChatOpen} onClose={() => setIsChatOpen(false)} />
      <ChatButton onClick={() => setIsChatOpen(true)} />
    </>
  );
};

export default Chatbot;

import { BicepsFlexed } from "lucide-react";

interface Props {
  onClick: () => void;
}

const ChatButton = ({ onClick }: Props) => {
  return (
    <button
      onClick={onClick}
      className="fixed bottom-6 right-6 w-16 h-16 rounded-full bg-violet-600 hover:bg-violet-500 text-white shadow-lg z-50 flex items-center justify-center transition-all"
    >
      <BicepsFlexed className="w-8 h-8" />
    </button>
  );
};

export default ChatButton;

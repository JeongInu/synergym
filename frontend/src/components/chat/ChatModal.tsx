import { X } from "lucide-react";

interface Props {
  isOpen: boolean;
  onClose: () => void;
}

const ChatModal = ({ isOpen, onClose }: Props) => {
  return (
    <div
      className={`fixed bottom-6 right-[7rem] w-80 h-96 bg-white rounded-xl shadow-xl border border-gray-200 z-50 transition-all duration-300 ${
        isOpen ? "opacity-100 translate-x-0" : "opacity-0 translate-x-4 pointer-events-none"
      }`}
    >
      <div className="flex justify-between items-center p-4 border-b">
        <span className="font-semibold text-lg">Gymmie</span>
        <button onClick={onClose}>
          <X className="w-5 h-5 text-gray-600 hover:text-black" />
        </button>
      </div>
      <div className="p-4 overflow-y-auto h-[calc(100%-60px)] text-sm text-gray-700">
        💬 어디가 불편하신가요?
      </div>
    </div>
  );
};

export default ChatModal;

import { useEffect, useRef } from "react";

export default function LiveThinkingBox({ text }) {
  const ref = useRef(null);

  useEffect(() => {
    if (ref.current) {
      ref.current.scrollTop = ref.current.scrollHeight;
    }
  }, [text]);

  return (
    <div
      ref={ref}
      className="  bg-black/80 text-gray-100 p-2 max-h-48 scrollbar-hide rounded-lg overflow-y-auto whitespace-pre-wrap"
    >
      {text}
    </div>
  );
}

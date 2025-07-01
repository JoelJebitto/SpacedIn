import { useEffect, useRef } from 'react'

export default function LiveThinkingBox({ text }) {
  const ref = useRef(null)

  useEffect(() => {
    if (ref.current) {
      ref.current.scrollTop = ref.current.scrollHeight
    }
  }, [text])

  return (
    <div
      ref={ref}
      className="border border-gray-600 bg-gray-800 text-gray-100 p-2 max-h-40 overflow-y-auto whitespace-pre-wrap"
    >
      {text}
    </div>
  )
}

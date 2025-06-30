import { useEffect, useRef } from 'react'

export default function RichTextEditor({ value, onChange, placeholder }) {
  const ref = useRef(null)

  useEffect(() => {
    if (ref.current && ref.current.innerHTML !== value) {
      ref.current.innerHTML = value || ''
    }
  }, [value])

  const handleInput = () => {
    if (ref.current) {
      onChange(ref.current.innerHTML)
    }
  }

  return (
    <div
      ref={ref}
      contentEditable
      onInput={handleInput}
      className="border p-2 min-h-[2.5rem] focus:outline-none [data-placeholder]:empty:before:content-[attr(data-placeholder)] [data-placeholder]:empty:before:text-gray-500"
      data-placeholder={placeholder}
    />
  )
}

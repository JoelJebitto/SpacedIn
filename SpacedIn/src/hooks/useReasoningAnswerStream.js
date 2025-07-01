import { useState, useRef, useCallback } from 'react'

/**
 * Custom hook to handle streamed reasoning/answer output.
 * Accumulates chunks until the stream ends, then splits the
 * "Reasoning:" and "Answer:" sections.
 */
export default function useReasoningAnswerStream() {
  const bufferRef = useRef('')
  const [streamText, setStreamText] = useState('')
  const [reasoningText, setReasoningText] = useState('')
  const [answerText, setAnswerText] = useState('')

  const handleChunk = useCallback(chunk => {
    bufferRef.current += chunk
    setStreamText(t => t + chunk)
  }, [])

  const handleEnd = useCallback(() => {
    const full = bufferRef.current
    bufferRef.current = ''
    setStreamText('')
    const match = full.match(/Reasoning:\s*([\s\S]*?)\n\s*Answer:\s*([\s\S]*)/i)
    if (match) {
      setReasoningText(match[1].trim())
      setAnswerText(match[2].trim())
    } else {
      // Fallback: only answer provided
      setAnswerText(full.trim())
      setReasoningText('')
    }
  }, [])

  const reset = useCallback(() => {
    bufferRef.current = ''
    setStreamText('')
    setReasoningText('')
    setAnswerText('')
  }, [])

  return { streamText, reasoningText, answerText, handleChunk, handleEnd, reset }
}

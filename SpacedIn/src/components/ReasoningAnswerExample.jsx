import { useState } from 'react'
import { api } from '../services/api'
import LiveThinkingBox from './LiveThinkingBox'
import useReasoningAnswerStream from '../hooks/useReasoningAnswerStream'

export default function ReasoningAnswerExample() {
  const [question, setQuestion] = useState('')
  const [isStreaming, setIsStreaming] = useState(false)
  const [stopStream, setStopStream] = useState(null)
  const {
    streamText,
    reasoningText,
    answerText,
    handleChunk,
    handleEnd,
    reset,
  } = useReasoningAnswerStream()

  const start = () => {
    if (stopStream) stopStream()
    reset()
    setIsStreaming(true)
    const close = api.streamAnswer(question, handleChunk, () => {
      handleEnd()
      setIsStreaming(false)
    })
    setStopStream(() => close)
  }

  return (
    <div className='space-y-2'>
      <input
        className='border p-2 w-full'
        value={question}
        onChange={e => setQuestion(e.target.value)}
        placeholder='Ask a question...'
      />
      <button
        onClick={start}
        disabled={isStreaming || !question.trim()}
        className='px-3 py-1 bg-blue-600 text-white rounded'
      >
        Ask
      </button>
      {isStreaming && <LiveThinkingBox text={streamText} />}
      <div id='reasoningBox' className='border border-gray-600 p-2 min-h-[50px]'>
        {reasoningText}
      </div>
      <div id='answerBox' className='border border-gray-600 p-2 min-h-[50px]'>
        {answerText}
      </div>
    </div>
  )
}

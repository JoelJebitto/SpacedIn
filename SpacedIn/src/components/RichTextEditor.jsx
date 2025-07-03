import { useEffect, useState } from "react";
import { Editor, EditorState, ContentState, convertFromHTML } from "draft-js";
import { stateToHTML } from "draft-js-export-html";
import "draft-js/dist/Draft.css";

const createStateFromHTML = (html) => {
  const blocks = convertFromHTML(html || "");
  const content = ContentState.createFromBlockArray(
    blocks.contentBlocks,
    blocks.entityMap,
  );
  return EditorState.createWithContent(content);
};

export default function RichTextEditor({ value, onChange, placeholder }) {
  const [editorState, setEditorState] = useState(() =>
    createStateFromHTML(value),
  );

  useEffect(() => {
    const current = stateToHTML(editorState.getCurrentContent());
    if (value !== current) {
      setEditorState(createStateFromHTML(value));
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [value]);

  const handleChange = (state) => {
    setEditorState(state);
    onChange(stateToHTML(state.getCurrentContent()));
  };

  return (
    <div className=" bg-black/80 text-gray-100 p-3 shadow-2xl border border-gray-800 rounded-md min-h-[2.5rem]">
      <Editor
        editorState={editorState}
        onChange={handleChange}
        placeholder={placeholder}
      />
    </div>
  );
}

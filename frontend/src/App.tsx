import { useState } from 'react'
import { useQuery, useMutation } from '@apollo/client'
import {
  TODOS_QUERY,
  CREATE_TODO,
  UPDATE_TODO,
  DELETE_TODO,
  type Todo,
  type TodosData,
} from './graphql/todos'

function App() {
  const [title, setTitle] = useState('')

  const { data, loading, error } = useQuery<TodosData>(TODOS_QUERY)

  const [createTodo, { error: createError }] = useMutation(CREATE_TODO, {
    refetchQueries: [TODOS_QUERY],
  })
  const [updateTodo] = useMutation(UPDATE_TODO, {
    refetchQueries: [TODOS_QUERY],
  })
  const [deleteTodo] = useMutation(DELETE_TODO, {
    refetchQueries: [TODOS_QUERY],
  })

  const handleCreate = async (e: React.FormEvent) => {
    e.preventDefault()
    if (!title.trim()) return
    await createTodo({ variables: { input: { title } } })
    setTitle('')
  }

  const handleToggle = (todo: Todo) => {
    updateTodo({
      variables: { id: todo.id, input: { completed: !todo.completed } },
    })
  }

  const handleDelete = (id: string) => {
    deleteTodo({ variables: { id } })
  }

  return (
    <div style={{ fontFamily: 'sans-serif', padding: '2rem', maxWidth: 600, margin: '0 auto' }}>
      <h1>Todo App</h1>

      <form onSubmit={handleCreate} style={{ display: 'flex', gap: '0.5rem', marginBottom: '1rem' }}>
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="やることを入力..."
          style={{ flex: 1, padding: '0.5rem' }}
        />
        <button type="submit" style={{ padding: '0.5rem 1rem' }}>
          追加
        </button>
      </form>

      {createError && <p style={{ color: 'red' }}>作成エラー: {createError.message}</p>}
      {loading && <p>読み込み中...</p>}
      {error && <p style={{ color: 'red' }}>エラー: {error.message}</p>}

      <ul style={{ listStyle: 'none', padding: 0 }}>
        {data?.todos.map((todo) => (
          <li
            key={todo.id}
            style={{
              display: 'flex',
              alignItems: 'center',
              gap: '0.5rem',
              padding: '0.5rem',
              borderBottom: '1px solid #eee',
            }}
          >
            <input
              type="checkbox"
              checked={todo.completed}
              onChange={() => handleToggle(todo)}
            />
            <span
              style={{
                flex: 1,
                textDecoration: todo.completed ? 'line-through' : 'none',
                color: todo.completed ? '#999' : 'inherit',
              }}
            >
              {todo.title}
            </span>
            <small style={{ color: '#999' }}>
              {todo.createdAt?.replace('T', ' ') ?? ''}
            </small>
            <button onClick={() => handleDelete(todo.id)} style={{ padding: '0.25rem 0.5rem' }}>
              削除
            </button>
          </li>
        ))}
      </ul>

      {data && data.todos.length === 0 && <p style={{ color: '#999' }}>Todoはまだありません</p>}
    </div>
  )
}

export default App

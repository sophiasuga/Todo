import { useQuery, gql } from '@apollo/client'

const HEALTH_QUERY = gql`
  query {
    health
  }
`

function App() {
  const { data, loading, error } = useQuery(HEALTH_QUERY)

  return (
    <div style={{ fontFamily: 'sans-serif', padding: '2rem' }}>
      <h1>Todo App</h1>
      <h2>疎通確認</h2>
      <div>
        {loading && <p>接続中...</p>}
        {error && <p style={{ color: 'red' }}>エラー: {error.message}</p>}
        {data && (
          <p style={{ color: 'green' }}>
            GraphQL: {data.health}
          </p>
        )}
      </div>
    </div>
  )
}

export default App

import { gql } from '@apollo/client'

export interface Todo {
  id: string
  title: string
  completed: boolean
  createdAt: string | null
}

export interface TodosData {
  todos: Todo[]
}

export const TODOS_QUERY = gql`
  query Todos {
    todos {
      id
      title
      completed
      createdAt
    }
  }
`

export const CREATE_TODO = gql`
  mutation CreateTodo($input: CreateTodoInput!) {
    createTodo(input: $input) {
      id
      title
      completed
      createdAt
    }
  }
`

export const UPDATE_TODO = gql`
  mutation UpdateTodo($id: ID!, $input: UpdateTodoInput!) {
    updateTodo(id: $id, input: $input) {
      id
      title
      completed
      createdAt
    }
  }
`

export const DELETE_TODO = gql`
  mutation DeleteTodo($id: ID!) {
    deleteTodo(id: $id)
  }
`

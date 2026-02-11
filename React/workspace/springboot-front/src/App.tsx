import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Signup from './routes/Signup'
import Login from './routes/Login'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<Login />} />
        <Route path='/signup' element={<Signup />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App

import './App.css'
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import Tailwindcss from './pages/Tailwindcss'
import Color from './pages/Color'
import TextsTest from './pages/TextsTest'

export default function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="*" element={<TextsTest />}/>
          <Route path="/1" element={<Color />}/>
          <Route path="/2" element={<Tailwindcss />}/>
        </Routes>
        <div>
          <Link to="*">TextTest</Link>/
          <Link to="/1">Color</Link>/
          <Link to="/2">Tailwindcss</Link>
        </div>
      </BrowserRouter>
    </div>
  )
}

import {Routes, Route} from 'react-router-dom'
import NoMatch from './Nomatch'
import Home from './Home'
import Board from './Board'

export default function RouterSetup(){
    return (
        <Routes>
            <Route path="*" element={<NoMatch />}/>
            <Route path="/" element={<Home />}/>
            <Route path="/welcome" element={<Home title="Welcome to out site" />}/>
            <Route path="/board/:boardId" element={<Board />} />
        </Routes>
    )
}
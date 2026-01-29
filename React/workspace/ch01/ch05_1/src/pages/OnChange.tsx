import { useState, type ChangeEvent } from "react"

export default function OnChange(){
    let [test, setText] = useState("Hello")
    const OnChangeValue = (e: ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation()
        e.preventDefault()
        console.log('onChangeValue', e.target.value)
        setText(e.target.value);
    }
    const onChangeChecked = (e: ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation()
        console.log('onChangeChecked', e.target.checked)
    }
    const onChangeFiles = (e: ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation()
        console.log('onChangeFiles', e.target.files)
    }

    return (
        <div>
            <p>OnChange</p>
            <p>{test}</p>
            <input type="text" onChange={OnChangeValue}
            placeholder="type some text" defaultValue="Hello"/>
            <input type="checkbox" onChange={onChangeChecked} defaultChecked/>
            <input type="file" onChange={onChangeFiles} multiple accept="image/*"/>
        </div>
    )
}
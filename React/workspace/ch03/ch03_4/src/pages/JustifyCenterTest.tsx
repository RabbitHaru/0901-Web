import {Div, Title, Subtitle} from '../components'
import * as D from '../data'

export default function JustifyCenterTest() {
  const boxes = D.range(0, 5).map(index => (
    <Div key={index} className="w-4 h-4 m-1 bg-black" />
  ))
  return (
    <section className="mt-4">
      <Title>JustifyCenterTest</Title>
      <div className="mt-4">
        <Subtitle>flex flex-row justify-center</Subtitle>
        <div className="flex flex-row justify-center bg-gray-300 h-30">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-col justify-center</Subtitle>
        <div className="flex flex-col justify-center bg-gray-300 h-30">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-row justify-start</Subtitle>
        <div className="flex flex-row justify-start bg-gray-300 h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-col justify-start</Subtitle>
        <div className="flex flex-col justify-start bg-gray-300 h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-row justify-end</Subtitle>
        <div className="flex flex-row justify-end bg-gray-300 h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-col justify-end</Subtitle>
        <div className="flex flex-col justify-end bg-gray-300 h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-row justify-between</Subtitle>
        <div className="flex flex-row justify-between bg-gray-300 h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-col justify-between</Subtitle>
        <div className="flex flex-col justify-between bg-gray-300 h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-row justify-around</Subtitle>
        <div className="flex flex-row justify-around bg-gray-300 h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-col justify-around</Subtitle>
        <div className="flex flex-col justify-around bg-gray-300 h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-row justify-center hover:justify-around</Subtitle>
        <div className="flex flex-row justify-center bg-gray-300 hover:justify-around h-60">{boxes}</div>
      </div>
      <div className="mt-4">
        <Subtitle>flex flex-col justify-center hover-justify-around</Subtitle>
        <div className="flex flex-col justify-center bg-gray-300 hover:justify-around h-60">{boxes}</div>
      </div>
    </section>
  )
}

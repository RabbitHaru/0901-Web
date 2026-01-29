import FileDrop from "./pages/FileDrop";
import DragDrop from "./pages/DragDrop";
import FileInput from "./pages/FileInput";
import OnChange from "./pages/OnChange";
import VariousInputs from "./pages/VariousInputs";
import StopPropagation from "./pages/StopPropagation";
import EventBubbling from "./pages/EventBubbling";
// import DispatchEvent from "./pages/DispatchEvent";
// import ReactOnClick from "./pages/ReactOnClick";
// import EventListener from "./pages/EventListener";
// import OnClick from "./pages/OnClick";

function App() {
  return (
    <div>
      <FileDrop />
      <DragDrop />
      <FileInput />
      <OnChange />
      <VariousInputs />
      <StopPropagation />
      <EventBubbling />
      {/* <DispatchEvent />
      <ReactOnClick />
      <OnClick />
      <EventListener /> */}
    </div>
  );
}

export default App;

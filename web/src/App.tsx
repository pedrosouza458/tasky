import { TaskForm } from "./components/task-form";
import { TasksList } from "./components/tasks-list";

function App() {
  return (
    <div>
      <h1 className="text-blue-700 font-semibold text-xl text-center mb-3">
        Tasky
      </h1>
      <div className="max-w-[450px] mx-auto">
        <div className="grid grid-cols-2">
          <TasksList />
          <TaskForm />
        </div>
      </div>
    </div>
  );
}

export default App;

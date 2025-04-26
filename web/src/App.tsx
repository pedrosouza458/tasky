import { useEffect, useState } from "react";
import "./App.css";
import axios from "axios";
import { Check, X } from "lucide-react";
import { createTask } from "./functions/create-task";
import { updateTaskStatus } from "./requests/update-task-status";

interface Task {
  id: string;
  title: string;
  description: string;
  done: boolean;
}

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);

    // You can now grab form fields like this:
    const title = formData.get("title") as string;
    const description = formData.get("description") as string;

    await createTask(title, description);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/tasks");
        const result = await response.data;
        setTasks(result);
      } catch (error) {
        console.error("Failed to fetch tasks:", error);
      }
    };
    fetchData();
  }, []);

  return (
    <div>
      <h1 className="text-blue-700 font-semibold text-xl">Tasky</h1>
      <div className="flex justify-center">
        <div className="">
          {tasks.map((task: Task) => (
            <div
              key={task.id}
              className="max-h-20 max-w-72 my-3 border border-zinc-600"
            >
              <div className="mx-3">
                <div className="flex justify-between">
                  <h1>{task.title}</h1>
                  <button
                    onClick={() => updateTaskStatus(task.id, task.done)}
                    className="cursor-pointer"
                  >
                    <p>{task.done === true ? <Check /> : <X />}</p>
                  </button>
                </div>
                <h2>{task.description}</h2>
              </div>
            </div>
          ))}
          <div>
            <h2>Adicionar Tarefa</h2>
            <form onSubmit={handleSubmit} className="flex flex-col gap-3 my-3">
              <input
                type="text"
                name="title"
                placeholder="Título"
                className="border border-zinc-500"
              />

              <input
                type="text"
                name="description"
                placeholder="Descrição"
                className="border border-zinc-500"
              />

              <button
                type="submit"
                className="bg-blue-700 rounded-md text-white cursor-pointer"
              >
                <h1 className="my-1"> Adicionar</h1>
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;

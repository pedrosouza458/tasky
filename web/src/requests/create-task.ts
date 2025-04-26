import axios from "axios";

export async function createTask(title: string, description: string) {
  await axios.post("http://localhost:8080/tasks", {
    title,
    description,
    limitDate: Date.now(),
  });
}

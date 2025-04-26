import axios from "axios";

export async function updateTaskStatus(id: string, status: boolean) {
  const newStatus = status === false ? true : false;
  try {
    await axios.put(`http://localhost:8080/tasks/${id}`, {
      done: newStatus,
    });
  } catch (error) {
    console.error("Error updating task status:", error);
  }
}

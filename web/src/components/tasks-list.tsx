"use client";

import { useEffect, useState } from "react";
import axios from "axios";
import { Task as TaskProps } from "../interfaces/task";
import { Task } from "./task";

export function TasksList() {
  const [tasks, setTasks] = useState<TaskProps[]>([]);

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
      <div className="flex justify-center">
        <div className="flex flex-col gap-3">
          {tasks.map((task: TaskProps) => (
            <Task
              key={task.id}
              id={task.id}
              title={task.title}
              description={task.description}
              done={task.done}
            />
          ))}
        </div>
      </div>
    </div>
  );
}

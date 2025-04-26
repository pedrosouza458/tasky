import { Check, X } from "lucide-react";
import { Task as TaskProps } from "../interfaces/task";
import { updateTaskStatus } from "../requests/update-task-status";

export function Task({ id, title, description, done }: TaskProps) {
  return (
    <div className="flex flex-col border gap-3">
      <div className="flex justify-between">
        <h1>{title}</h1>
        <button
          onClick={() => updateTaskStatus(id, done)}
          className="cursor-pointer"
        >
          <p>{done === true ? <Check /> : <X />}</p>
        </button>
      </div>
      <h2>{description}</h2>
    </div>
  );
}

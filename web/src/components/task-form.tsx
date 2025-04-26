"use client";

import { createTask } from "../requests/create-task";

export function TaskForm() {
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);

    // You can now grab form fields like this:
    const title = formData.get("title") as string;
    const description = formData.get("description") as string;

    await createTask(title, description);
  };

  return (
    <div className="my-3">
      <h2 className="text-center">Adicionar Tarefa</h2>
      <div className="flex justify-center items-center">
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
  );
}

package dawidbialek.todolist.services;

import dawidbialek.todolist.entities.Task;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    // list, getById, save, update, delete, patch

    Page<Task> listTasks();

    Optional<Task> getTaskById(UUID taskId);

    Task saveNewTask(Task task);

    Optional<Task> updateTaskById(UUID taskId, Task task);

    Boolean deleteTaskById(UUID id);

    Optional<Task> patchTaskById(UUID taskId, Task task);
}

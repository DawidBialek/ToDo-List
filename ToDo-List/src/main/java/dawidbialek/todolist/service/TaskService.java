package dawidbialek.todolist.service;

import dawidbialek.todolist.model.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    // list, getById, save, update, delete, patch

    List<TaskDTO> listTasks();

    Optional<TaskDTO> getTaskById(int taskId);

    TaskDTO saveNewTask(TaskDTO task);

    TaskDTO updateTaskById(int taskId, TaskDTO task);

    Boolean deleteTaskById(int id);

    Optional<TaskDTO> patchTaskById(int taskId, TaskDTO task);
}

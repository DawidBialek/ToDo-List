package dawidbialek.todolist.service;

import dawidbialek.todolist.model.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    // list, getById, save, update, delete, patch

    List<TaskDTO> listTasks();

    Optional<TaskDTO> getTaskById(Integer taskId);

    TaskDTO saveNewTask(TaskDTO task);

    Optional<TaskDTO> updateTaskById(Integer taskId, TaskDTO task);

    Boolean deleteTaskById(Integer id);

    Optional<TaskDTO> patchTaskById(Integer taskId, TaskDTO task);
}

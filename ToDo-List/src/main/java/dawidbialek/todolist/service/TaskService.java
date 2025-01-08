package dawidbialek.todolist.service;

import dawidbialek.todolist.entity.Task;
import dawidbialek.todolist.model.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    // list, getById, save, update, delete, patch

    List<TaskDTO> listTasks();

    Optional<TaskDTO> getTaskById(UUID taskId);

    Task saveNewTask(TaskDTO task);

    Optional<TaskDTO> updateTaskById(UUID taskId, TaskDTO task);

    Boolean deleteTaskById(UUID id);

    Optional<TaskDTO> patchTaskById(UUID taskId, TaskDTO task);
}

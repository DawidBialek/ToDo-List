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

    Optional<TaskDTO> getTaskById(int taskId);

    Task saveNewTask(TaskDTO task);

    Optional<TaskDTO> updateTaskById(int taskId, TaskDTO task);

    Boolean deleteTaskById(int id);

    Optional<TaskDTO> patchTaskById(int taskId, TaskDTO task);
}

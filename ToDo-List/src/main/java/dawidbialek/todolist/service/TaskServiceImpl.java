package dawidbialek.todolist.service;

import dawidbialek.todolist.entity.Task;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public class TaskServiceImpl implements TaskService {
    @Override
    public Page<Task> listTasks() {
        return null;
    }

    @Override
    public Optional<Task> getTaskById(UUID taskId) {
        return Optional.empty();
    }

    @Override
    public Task saveNewTask(Task task) {
        return null;
    }

    @Override
    public Optional<Task> updateTaskById(UUID taskId, Task task) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteTaskById(UUID id) {
        return null;
    }

    @Override
    public Optional<Task> patchTaskById(UUID taskId, Task task) {
        return Optional.empty();
    }
}

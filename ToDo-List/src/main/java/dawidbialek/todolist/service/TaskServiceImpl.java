package dawidbialek.todolist.service;

import dawidbialek.todolist.entity.Task;
import dawidbialek.todolist.mapper.TaskMapper;
import dawidbialek.todolist.model.TaskDTO;
import dawidbialek.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDTO> listTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::taskToTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDTO> getTaskById(int taskId) {
        return taskRepository.findById(taskId).map(taskMapper::taskToTaskDTO);
    }

    // TODO: Implement methods

    @Override
    public Task saveNewTask(TaskDTO task) {
        return null;
    }

    @Override
    public Optional<TaskDTO> updateTaskById(int taskId, TaskDTO task) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteTaskById(int id) {
        return null;
    }

    @Override
    public Optional<TaskDTO> patchTaskById(int taskId, TaskDTO task) {
        return Optional.empty();
    }
}

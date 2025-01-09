package dawidbialek.todolist.service;

import dawidbialek.todolist.entity.Task;
import dawidbialek.todolist.mapper.TaskMapper;
import dawidbialek.todolist.model.TaskDTO;
import dawidbialek.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public TaskDTO saveNewTask(TaskDTO task) {
        return taskMapper.taskToTaskDTO(taskRepository.save(taskMapper.taskDTOToTask(task)));
    }

    @Override
    public TaskDTO updateTaskById(int taskId, TaskDTO task) {
        Task updatedTask = taskRepository.findById(taskId).orElseThrow();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setDeadline(task.getDeadline());
        updatedTask.setPriority(task.getPriority());
        return taskMapper.taskToTaskDTO(taskRepository.save(updatedTask));
    }

    @Override
    public Boolean deleteTaskById(int id) {
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<TaskDTO> patchTaskById(int taskId, TaskDTO task) {
        return Optional.empty();
    }
}

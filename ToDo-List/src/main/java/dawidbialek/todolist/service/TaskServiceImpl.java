package dawidbialek.todolist.service;

import dawidbialek.todolist.entity.Priority;
import dawidbialek.todolist.model.TaskDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class TaskServiceImpl implements TaskService {

    private HashMap<Integer, TaskDTO> taskMap;

    public TaskServiceImpl() {
        this.taskMap = new HashMap<>();

        TaskDTO task1 = TaskDTO.builder()
                .id(1)
                .title("task1")
                .description("description1")
                .priority(Priority.MEDIUM)
                .creationTime(LocalDateTime.now())
                .deadline(LocalDateTime.of(2025, Month.JUNE, 25, 5, 0))
                .build();

        TaskDTO task2 = TaskDTO.builder()
                .id(2)
                .title("task2")
                .description("description2")
                .priority(Priority.HIGH)
                .creationTime(LocalDateTime.now())
                .deadline(LocalDateTime.of(2025, Month.JULY, 25, 5, 0))
                .build();

        TaskDTO task3 = TaskDTO.builder()
                .id(3)
                .title("task3")
                .description("description3")
                .priority(Priority.LOW)
                .creationTime(LocalDateTime.now())
                .deadline(LocalDateTime.of(2025, Month.OCTOBER, 25, 5, 0))
                .build();

        taskMap.put(task1.getId(), task1);
        taskMap.put(task2.getId(), task2);
        taskMap.put(task3.getId(), task3);
    }

    @Override
    public List<TaskDTO> listTasks() {
        return new ArrayList<TaskDTO>(taskMap.values());
    }

    @Override
    public Optional<TaskDTO> getTaskById(Integer taskId) {
        return Optional.ofNullable(taskMap.get(taskId));
    }

    @Override
    public TaskDTO saveNewTask(TaskDTO task) {
        TaskDTO savedTask = TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .creationTime(task.getCreationTime())
                .deadline(task.getDeadline())
                .build();

        taskMap.put(savedTask.getId(), savedTask);

        return savedTask;
    }

    @Override
    public Optional<TaskDTO> updateTaskById(Integer taskId, TaskDTO task) {
        TaskDTO existingTask = taskMap.get(taskId);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setCreationTime(task.getCreationTime());
        existingTask.setDeadline(task.getDeadline());

        taskMap.put(taskId, existingTask);

        return Optional.of(existingTask);
    }

    @Override
    public Boolean deleteTaskById(Integer id) {
        taskMap.remove(id);

        return true;
    }

    @Override
    public Optional<TaskDTO> patchTaskById(Integer taskId, TaskDTO task) {
        TaskDTO existingTask = taskMap.get(taskId);

        if(StringUtils.hasText(task.getTitle())){
            existingTask.setTitle(task.getTitle());
        }

        if(StringUtils.hasText(task.getDescription())){
            existingTask.setDescription(task.getDescription());
        }

        if(task.getPriority() != null){
            existingTask.setPriority(task.getPriority());
        }

        if(task.getDeadline() != null){
            existingTask.setDeadline(task.getDeadline());
        }

        return Optional.empty();
    }
}

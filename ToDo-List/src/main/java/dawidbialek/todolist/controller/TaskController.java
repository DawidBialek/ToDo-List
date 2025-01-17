package dawidbialek.todolist.controller;

import dawidbialek.todolist.entity.Task;
import dawidbialek.todolist.model.TaskDTO;
import dawidbialek.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class TaskController {

    public static final String TASK_PATH = "/api/v1/task";
    public static final String TASK_PATH_ID = TASK_PATH + "/{taskId}";

    private final TaskService taskService;

    @GetMapping(value = TASK_PATH_ID)
    public TaskDTO getTaskById(@PathVariable("taskId") int taskId) throws ChangeSetPersister.NotFoundException {
        return taskService.getTaskById(taskId).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @GetMapping(value = TASK_PATH + "s")
    public List<TaskDTO> getTasks() {
        return taskService.listTasks();
    }

    @PostMapping(TASK_PATH)
    public TaskDTO createTask(@RequestBody TaskDTO task){
        return taskService.saveNewTask(task);
    }

    @PutMapping(TASK_PATH_ID)
    public Optional<TaskDTO> updateTask(@PathVariable("taskId") int id, @RequestBody TaskDTO task){
        return taskService.updateTaskById(id, task);
    }

    @DeleteMapping(TASK_PATH_ID)
    public Boolean deleteTask(@PathVariable("taskId") int id){
        return taskService.deleteTaskById(id);
    }
}

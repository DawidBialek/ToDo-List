package dawidbialek.todolist.controller;

import dawidbialek.todolist.model.TaskDTO;
import dawidbialek.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public TaskDTO getTaskById(@PathVariable("taskId") int taskId) {
        return taskService.getTaskById(taskId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = TASK_PATH)
    public List<TaskDTO> getTasks() {
        return taskService.listTasks();
    }

    @PostMapping(TASK_PATH)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@RequestBody TaskDTO task){
        return taskService.saveNewTask(task);
    }

    @PutMapping(TASK_PATH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Optional<TaskDTO> updateTask(@PathVariable("taskId") int id, @RequestBody TaskDTO task){
        return taskService.updateTaskById(id, task);
    }

    @DeleteMapping(TASK_PATH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteTask(@PathVariable("taskId") int id){
        return taskService.deleteTaskById(id);
    }

    @PatchMapping(TASK_PATH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Optional<TaskDTO> patchBeerById(@PathVariable("taskId") Integer taskId, @RequestBody TaskDTO task) {

        return taskService.patchTaskById(taskId, task);
    }
}

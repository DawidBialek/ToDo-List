package dawidbialek.todolist.controller;

import dawidbialek.todolist.model.TaskDTO;
import dawidbialek.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
}

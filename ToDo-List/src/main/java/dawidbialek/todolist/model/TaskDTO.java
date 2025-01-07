package dawidbialek.todolist.model;

import dawidbialek.todolist.entity.Priority;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class TaskDTO {
    private int id;
    private String title;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime deadline;
    private Priority priority;
}

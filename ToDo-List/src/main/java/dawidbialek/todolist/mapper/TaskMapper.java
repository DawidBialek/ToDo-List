package dawidbialek.todolist.mapper;

import dawidbialek.todolist.entity.Task;
import dawidbialek.todolist.model.TaskDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {

    Task taskDTOToTask(TaskDTO taskDTO);
    TaskDTO taskToTaskDTO(Task task);
}

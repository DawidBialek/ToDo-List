package dawidbialek.todolist.repository;

import dawidbialek.todolist.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}

package dawidbialek.todolist.repository;

import dawidbialek.todolist.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    @Override
    List<Task> findAll();
}

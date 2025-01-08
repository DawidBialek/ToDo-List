package dawidbialek.todolist.bootstrap;

import dawidbialek.todolist.entity.Priority;
import dawidbialek.todolist.entity.Task;
import dawidbialek.todolist.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        setupTaskRepository();
    }

    public void setupTaskRepository() {

        if(taskRepository.count() == 0) {
            Task task1 = Task.builder()
                    .title("task 1")
                    .description("description 1")
                    .priority(Priority.MEDIUM)
                    .deadline(LocalDateTime.parse("2024-12-14T10:15:30"))
                    .build();

            Task task2 = Task.builder()
                    .title("task 2")
                    .description("description 2")
                    .priority(Priority.MEDIUM)
                    .deadline(LocalDateTime.parse("2024-12-15T10:15:30"))
                    .build();

            Task task3 = Task.builder()
                    .title("task 3")
                    .description("description 3")
                    .priority(Priority.MEDIUM)
                    .deadline(LocalDateTime.parse("2024-12-16T10:15:30"))
                    .build();

            taskRepository.save(task1);
            taskRepository.save(task2);
            taskRepository.save(task3);
        }
    }

}

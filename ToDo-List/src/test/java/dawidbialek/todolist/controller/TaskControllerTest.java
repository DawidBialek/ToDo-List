package dawidbialek.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dawidbialek.todolist.config.SpringSecConfig;
import dawidbialek.todolist.model.TaskDTO;
import dawidbialek.todolist.service.TaskService;
import dawidbialek.todolist.service.TaskServiceImpl;
import dawidbialek.todolist.service.TaskServiceImplJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@WebMvcTest
@Import(SpringSecConfig.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TaskService taskService;

    @Captor
    ArgumentCaptor<Integer> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<TaskDTO> taskDTOArgumentCaptor;

    TaskServiceImpl taskServiceImpl;

    Random random = new Random();

    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    @BeforeEach
    void setUp() {
        taskServiceImpl = new TaskServiceImpl();
    }

    @Test
    void getBeerById() throws Exception {
        TaskDTO testTask = taskServiceImpl.listTasks().getFirst();

        given(taskService.getTaskById(any(Integer.class))).willReturn(Optional.of(testTask));

        mockMvc.perform(get(TaskController.TASK_PATH_ID, random.nextInt())
                .with(httpBasic(USERNAME, PASSWORD))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testTask.getId())))
                .andExpect(jsonPath("$.title", is(testTask.getTitle().toString())));
    }

    @Test
    void getTaskByIdNotFound() throws Exception {

        given(taskService.getTaskById(any(Integer.class))).willReturn(Optional.empty());

        mockMvc.perform(get(TaskController.TASK_PATH_ID, random.nextInt())
                        .with(httpBasic(USERNAME, PASSWORD)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testListAllTasks() throws Exception {

        given(taskService.listTasks()).willReturn(taskServiceImpl.listTasks());

        mockMvc.perform(get(TaskController.TASK_PATH)
                .with(httpBasic(USERNAME, PASSWORD))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));


    }

    @Test
    void testCreateNewTask() throws Exception {
        TaskDTO task = taskServiceImpl.listTasks().getFirst();
        task.setId(null);

        given(taskService.saveNewTask(any(TaskDTO.class))).willReturn(taskServiceImpl.listTasks().getFirst());

        mockMvc.perform(post(TaskController.TASK_PATH)
                .with(httpBasic(USERNAME, PASSWORD))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated());
    }

}
package dawidbialek.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dawidbialek.todolist.config.SpringSecConfig;
import dawidbialek.todolist.model.TaskDTO;
import dawidbialek.todolist.service.TaskService;
import dawidbialek.todolist.service.TaskServiceImpl;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    ArgumentCaptor<Integer> integerArgumentCaptor;

    @Captor
    ArgumentCaptor<TaskDTO> taskArgumentCaptor;

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

    @Test
    void testUpdateTask() throws Exception {
        TaskDTO task = taskServiceImpl.listTasks().getFirst();

        given(taskService.updateTaskById(any(), any())).willReturn(Optional.of(TaskDTO.builder().build()));

        mockMvc.perform(put(TaskController.TASK_PATH_ID, task.getId())
                        .with(httpBasic(USERNAME, PASSWORD))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isNoContent());

        verify(taskService).updateTaskById(any(Integer.class), any(TaskDTO.class));
    }

    @Test
    void testDeleteTask() throws Exception {
        TaskDTO task = taskServiceImpl.listTasks().getFirst();

        given(taskService.deleteTaskById(any())).willReturn(true);

        mockMvc.perform(delete(TaskController.TASK_PATH_ID, task.getId())
                        .with(httpBasic(USERNAME, PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(taskService).deleteTaskById(argumentCaptor.capture());

        assertThat(task.getId()).isEqualTo(argumentCaptor.getValue());
    }

    @Test
    void testPatchTask() throws Exception {
        TaskDTO task = taskServiceImpl.listTasks().getFirst();

        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("title", "New Title");

        mockMvc.perform(patch(TaskController.TASK_PATH_ID, task.getId())
                        .with(httpBasic(USERNAME, PASSWORD))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskMap)))
                .andExpect(status().isNoContent());

        verify(taskService).patchTaskById(integerArgumentCaptor.capture(), taskArgumentCaptor.capture());

        assertThat(task.getId()).isEqualTo(integerArgumentCaptor.getValue());
        assertThat(taskMap.get("title")).isEqualTo(taskArgumentCaptor.getValue().getTitle());
    }

}
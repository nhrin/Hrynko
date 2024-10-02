package com.example.todoapp.service;

import com.example.todoapp.entity.AppUser;
import com.example.todoapp.entity.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    private Task task;
    private AppUser user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new AppUser();
        user.setUsername("test_name");

        task = new Task();
        task.setId(1L);
        task.setDescription("Test Task");
        task.setUser(user);
    }

    @Test
    public void testCreateTask() {
        when(userRepository.findByUsername("test_name")).thenReturn(user);
        when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task, "test_name");

        assertEquals("Test Task", createdTask.getDescription());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testGetTasksByUser() {
        when(userRepository.findByUsername("test_name")).thenReturn(user);
        when(taskRepository.findByUserUsername("test_name")).thenReturn(Arrays.asList(task));

        List<Task> tasks = taskService.getTasksByUser("test_name");

        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getDescription());
    }

    @Test
    public void testUpdateTask() {
        Task updatedTask = new Task();
        updatedTask.setDescription("Updated Task");
        updatedTask.setCompleted(true);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.updateTask(1L, updatedTask);

        assertEquals("Updated Task", result.getDescription());
        assertEquals(true, result.getCompleted());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testDeleteTask() {
        doNothing().when(taskRepository).deleteById(1L);
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}

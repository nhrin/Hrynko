package com.example.todoapp.service;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.AppUser;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task createTask(Task task, String username) {
        AppUser user = userRepository.findByUsername(username);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUser(String username) {
        return taskRepository.findByUserUsername(username);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.getCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void uploadFile(Long taskId, MultipartFile file) throws IOException {
        Task task = taskRepository.findById(taskId).orElseThrow();
        String filePath = "uploads/" + file.getOriginalFilename();
        Files.write(Paths.get(filePath), file.getBytes());
        task.setFilePath(filePath);
        taskRepository.save(task);
    }
}

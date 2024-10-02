package com.example.todoapp.controller;

import com.example.todoapp.entity.Task;
import com.example.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task, Principal principal) {
        taskService.createTask(task, principal.getName());
        return ResponseEntity.ok("Task created");
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(Principal principal) {
        return ResponseEntity.ok(taskService.getTasksByUser(principal.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return ResponseEntity.ok("Task updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }

    @PostMapping("/upload/{taskId}")
    public ResponseEntity<String> uploadFile(@PathVariable Long taskId, @RequestParam("file") MultipartFile file) throws IOException {
        taskService.uploadFile(taskId, file);
        return ResponseEntity.ok("File uploaded");
    }
}

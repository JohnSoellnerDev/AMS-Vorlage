package com.school.john.controller;

import com.school.john.entity.Task;
import com.school.john.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    @Autowired
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable Long id) {
        try {
            return new ResponseEntity<Task>(
                    taskService.getTaskByID(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Task> saveNewTask(@RequestBody Task task) {
        try {
            return new ResponseEntity<Task>(
                    taskService.saveTask(task), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Task> updateTaskByID(@RequestBody Task task) {
        try {
            return new ResponseEntity<Task>(
                    taskService.saveTask(task), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTaskByID(@PathVariable Long id) {
        try {
            taskService.deleteTaskByID(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

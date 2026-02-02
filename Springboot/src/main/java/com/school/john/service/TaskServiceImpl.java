package com.school.john.service;

import com.school.john.entity.Task;
import com.school.john.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return determineActiveTasks(tasks);
    }

    private List<Task> determineActiveTasks(List<Task> tasks) {
        return tasks.stream()
                .filter(Task::isActive)
                .toList();
    }

    @Override
    public Task getTaskByID(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskByID(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}

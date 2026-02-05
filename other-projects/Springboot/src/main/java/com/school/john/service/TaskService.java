package com.school.john.service;

import com.school.john.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskByID(Long id);

    Task saveTask(Task task);

    void deleteTaskByID(Long id);
}

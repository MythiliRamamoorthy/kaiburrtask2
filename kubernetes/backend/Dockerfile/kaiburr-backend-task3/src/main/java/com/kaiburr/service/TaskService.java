package com.kaiburr.service;

import com.kaiburr.model.Task;
import com.kaiburr.model.TaskExecution;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaskService {
    private final Map<String, Task> taskDB = new ConcurrentHashMap<>();

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskDB.values());
    }

    public Task getTask(String id) {
        return taskDB.get(id);
    }

    public void addTask(Task task) {
        taskDB.put(task.id, task);
    }

    public void deleteTask(String id) {
        taskDB.remove(id);
    }

    public List<Task> findTasksByName(String namePart) {
        List<Task> results = new ArrayList<>();
        for (Task task : taskDB.values()) {
            if (task.name != null && task.name.contains(namePart)) {
                results.add(task);
            }
        }
        return results;
    }

    public void executeTask(String taskId) {
        Task task = taskDB.get(taskId);
        if (task != null) {
            TaskExecution execution = new TaskExecution();
            execution.startTime = new Date();
            try {
                Process process = Runtime.getRuntime().exec(task.command);
                Scanner scanner = new Scanner(process.getInputStream()).useDelimiter("\\A");
                execution.output = scanner.hasNext() ? scanner.next() : "";
                scanner.close();
                process.waitFor();
            } catch (Exception e) {
                execution.output = "Error: " + e.getMessage();
            }
            execution.endTime = new Date();
            task.taskExecutions.add(execution);
        }
    }
}

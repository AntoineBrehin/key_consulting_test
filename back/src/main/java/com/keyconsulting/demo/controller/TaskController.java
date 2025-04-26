package com.keyconsulting.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyconsulting.demo.model.Task;
import com.keyconsulting.demo.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<Task>> fetchAllTask() {
        try {
            List<Task> allTasks = this.taskService.getAllTasks();
            return ResponseEntity.status(HttpStatus.OK).body(allTasks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> fetchTaskById(@PathVariable String taskId) {
        try {
            Task task = this.taskService.getTaskById(taskId);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/todo")
    public ResponseEntity<List<Task>> fetchAllTaskToComplete() {
        try {
            List<Task> taskToComplete = this.taskService.getAllTaskToComplete();
            return ResponseEntity.status(HttpStatus.OK).body(taskToComplete);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Task> addNewTask(@RequestBody Task newTask) {
        try {
            newTask.setId(this.taskService.generateNewId());
            this.taskService.addNewTask(newTask);
            return ResponseEntity.status(HttpStatus.OK).body(newTask);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable String taskId,
            @RequestParam boolean newStatus) {
        try {
            Task taskEdited = this.taskService.updateTaskStatus(taskId, newStatus);
            if (taskEdited != null) {
                return ResponseEntity.status(HttpStatus.OK).body(taskEdited);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

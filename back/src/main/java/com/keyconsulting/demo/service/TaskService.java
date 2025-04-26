package com.keyconsulting.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.keyconsulting.demo.model.Task;

import jakarta.annotation.PostConstruct;

@Service
public class TaskService {

    private List<Task> tasksList = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Chargement de données en mémoire à l'initialisation du service
        tasksList.add(new Task("uuid-example-1", "Sortir Médor", "Sortir l'animal de la famille des canidé afin de générer du bonheur", false));
        tasksList.add(new Task("uuid-example-2", "Faire la lessive", "Laver le linge afin d'avoir quelque chose à se mettre demain", false));
        tasksList.add(new Task("uuid-example-3", "Préparer le dessert", "Cuisiner un délicieux Tiramisu ", true));
        tasksList.add(new Task("uuid-example-4", "Préparer la soirée JDR", "Ecrire un scénario correct qu'aucun joueur ne suivra de toute façon", false));
        
    }

    public List<Task> getAllTasks() {
        return this.tasksList;
    }

    public Task getTaskById(String id) {
        return this.tasksList.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Task> getAllTaskToComplete() {
        return this.tasksList.stream().filter(task -> task.getCompleted() == false).toList();
    }

    public void addNewTask(Task task) {
        this.tasksList.add(task);
    }

    public Task updateTaskStatus(String taskId, boolean taskStatus) {
        for (Task task : this.tasksList) {
            if (task.getId().equals(taskId)) {
                task.setCompleted(taskStatus);
                return task;
            }
        }
        return null;
    }

    public String generateNewId() {
        return UUID.randomUUID().toString();
    }
}

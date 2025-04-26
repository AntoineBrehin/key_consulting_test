import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Task, TaskService } from '../../services/task.service';

@Component({
  selector: 'app-data-table',
  imports: [CommonModule, MatExpansionModule, MatButtonModule, MatIconModule],
  templateUrl: './data-table.component.html',
  styleUrl: './data-table.component.scss'
})
export class DataTableComponent {

  tasksList: Task[] = [];
  private filterTaskToComplete: boolean = false;

  constructor(public taskService: TaskService) {
    this.loadAllTasks();
  }

  editTaskStatus(task:Task){
    const status: boolean = !task.completed;
    this.taskService.updateTaskStatus(task.id, status).subscribe({
      next:(data:Task) => {
        task.completed = status;
      },
      error:(error:any) => {
        console.error("Une erreur est survenue lors de la modification du statut");
      }
    })
  }

  filterTask(){
    this.filterTaskToComplete = !this.filterTaskToComplete;

    if(this.filterTaskToComplete){
      this.loadTasksToComplete();
    }
    else {
      this.loadAllTasks();
    }
  }

  loadAllTasks(){
    this.taskService.fetchAllTasks().subscribe({
      next: (data: Task[]) => {
        this.tasksList = [...data];
      },
      error: (error: any) => {
        console.error("Une erreur a été rencontrée lors du chargement des tâches.");
      }
    });
  }

  loadTasksToComplete(){
    this.taskService.fetchAllTasksToComplete().subscribe({
      next: (data: Task[]) => {
        this.tasksList = [...data];
      },
      error: (error: any) => {
        console.error("Une erreur a été rencontrée lors du chargement des tâches à compléter.");
      }
    });
  }
  
}

import { Component, ViewChild } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { DataTableComponent } from '../data-table/data-table.component';
import { CreateTaskComponent } from '../create-task/create-task.component';
import { Task, TaskService } from '../../services/task.service';
import { environment } from '../../../environments/environment.development';

@Component({
  selector: 'app-home',
  imports: [MatToolbarModule, DataTableComponent, MatButtonModule, MatIconModule, MatTooltipModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  @ViewChild(DataTableComponent) datatableComponent!: DataTableComponent;

  constructor(private dialog: MatDialog, public taskService: TaskService) {

  }

  filterByCompleted() {
    this.datatableComponent.filterTask();
  }

  openCreateTaskDialog() {
    const dialogRef = this.dialog.open(CreateTaskComponent, {
      width: '400vw'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result);
        const newTask: Task = {
          id: '',
          label: result.label,
          description: result.description,
          completed: false
        }
        this.taskService.createNewTask(newTask).subscribe({
          next: (data: Task) => {
            this.datatableComponent.loadAllTasks();
          },
          error: (error: any) => {
            console.error("Une erreur est survenue lors de la création de la nouvelle tâche");
          }
        })
      }
    });
  }

  openSwaggerUrl(){
    window.open(environment.apiURL+"/swagger-ui.html","_blank");
  }
}

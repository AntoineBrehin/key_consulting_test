import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  public fetchAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(environment.apiURL + "/task");
  }

  public fetchAllTasksToComplete(): Observable<Task[]> {
    return this.http.get<Task[]>(environment.apiURL + "/task/todo");
  }

  public fetchTaskById(taskId: string): Observable<Task> {
    return this.http.get<Task>(environment.apiURL + "/task/" + taskId);
  }

  public createNewTask(task: Task): Observable<Task> {
    return this.http.post<Task>(environment.apiURL + "/task", task);
  }

  public updateTaskStatus(taskId: string, taskStatus: boolean): Observable<Task> {
    return this.http.put<Task>(environment.apiURL + "/task/" + taskId + "/status?newStatus=" + taskStatus, {});
  }
}

export interface Task {
  id: string;
  label: string;
  description: string;
  completed: boolean;
}

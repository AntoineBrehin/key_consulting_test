import { TestBed } from '@angular/core/testing';

import { Task, TaskService } from './task.service';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { environment } from '../../environments/environment';
import { provideHttpClient } from '@angular/common/http';

describe('TaskService', () => {
  let service: TaskService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        TaskService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });

    service = TestBed.inject(TaskService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should fetch only uncompleted task', () => {
    const mockTasks: Task[] = [
      { id: 'uuid-example-1', label: 'Tâche 1', description: 'Description 1', completed: false },
      { id: 'uuid-example-2', label: 'Tâche 2', description: 'Description 2', completed: true },
      { id: 'uuid-example-3', label: 'Tâche 3', description: 'Description 3', completed: false }
    ];

    service.fetchAllTasksToComplete().subscribe(tasks => {
      expect(mockTasks.filter(task => task.completed == false).length).toBe(2);
    });
    const req = httpMock.expectOne(`${environment.apiURL}/task/todo`);
    expect(req.request.method).toBe('GET');
    req.flush(mockTasks);
  });

  it('should create a new task', () => {
    const newTask: Task = { id: '', label: 'Nouvelle Tâche', description: 'Description de la nouvelle tâche', completed: false };
    const createdTask: Task = { ...newTask, id: '4' };

    service.createNewTask(newTask).subscribe(task => {
      expect(task).toEqual(createdTask);
    });

    const req = httpMock.expectOne(`${environment.apiURL}/task`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newTask);
    req.flush(createdTask);
  });


  it('should update task status', () => {
    const updatedTask: Task = { id: 'uuid-example-1', label: 'Tâche 1', description: 'Description 1', completed: true };
  
    service.updateTaskStatus('uuid-example-1', true).subscribe(task => {
      expect(task).toEqual(updatedTask);
    });
  
    const req = httpMock.expectOne(`${environment.apiURL}/task/uuid-example-1/status?newStatus=true`);
    expect(req.request.method).toBe('PUT');
    req.flush(updatedTask);
  });

});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataTableComponent } from './data-table.component';
import { Task, TaskService } from '../../services/task.service';
import { of } from 'rxjs';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('DataTableComponent', () => {
  let component: DataTableComponent;
  let fixture: ComponentFixture<DataTableComponent>;
  let mockTaskService: jasmine.SpyObj<TaskService>;

  const mockTasks: Task[] = [
    { id: 'uuid-example-1', label: 'Task 1', description: '', completed: false },
    { id: 'uuid-example-2', label: 'Task 2', description: '', completed: true }
  ];

  beforeEach(async () => {
    mockTaskService = jasmine.createSpyObj('TaskService', [
      'fetchAllTasks', 'fetchAllTasksToComplete', 'updateTaskStatus'
    ]);
    mockTaskService.fetchAllTasks.and.returnValue(of(mockTasks));

    await TestBed.configureTestingModule({
      imports: [DataTableComponent],
      providers: [{ provide: TaskService, useValue: mockTaskService },
      provideHttpClient(),
      provideHttpClientTesting()]
    }).compileComponents();

    fixture = TestBed.createComponent(DataTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load all tasks on init', () => {
    expect(component.tasksList.length).toBe(2);
    expect(mockTaskService.fetchAllTasks).toHaveBeenCalled();
  });

  it('should edit task status', () => {
    const task = { id: 'uuid-example-1', label: 'Task 1', description: '', completed: false };
    mockTaskService.updateTaskStatus.and.returnValue(of({ ...task, completed: true }));

    component.editTaskStatus(task);

    expect(mockTaskService.updateTaskStatus).toHaveBeenCalledWith('uuid-example-1', true);
    expect(task.completed).toBeTrue();
  });

  it('should filter tasks to complete', () => {
    const incompleteTasks: Task[] = [{ id: 'uuid-example-3', label: 'Task 3', description: '', completed: false }];
    mockTaskService.fetchAllTasksToComplete.and.returnValue(of(incompleteTasks));

    component.filterTask();

    expect(component.tasksList.length).toBe(1);
    expect(mockTaskService.fetchAllTasksToComplete).toHaveBeenCalled();
  });

});
